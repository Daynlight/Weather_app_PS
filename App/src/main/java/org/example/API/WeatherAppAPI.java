package org.example;

import org.example.Terminal;
import org.example.Config;
import org.example.ApiCache;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.io.File;
import java.time.Instant;
import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class WeatherAppAPI {
  Terminal terminal = new Terminal();
  private String respond = null;
  private String url = null;
  private Instant timestamp = null;



  private String request(String url) {
    if(url.equals(this.url) && 
       respond != null && 
       timestamp != null &&
       Duration.between(timestamp, Instant.now()).toSeconds() < Config.CACHELIFETIME){
      terminal.printLog("Used cached request");
      return respond;
    };

    loadFromCache(url);

    if(respond != null && 
       timestamp != null &&
       Duration.between(timestamp, Instant.now()).toSeconds() < Config.CACHELIFETIME){
        terminal.printLog("Used cached request from file");
        return respond;
      };

    try(CloseableHttpClient httpClient = HttpClients.createDefault()){
      HttpGet getRequest = new HttpGet(url);
            
      getRequest.addHeader("accept", "application/json");
      
      terminal.printLog("Sending request");
      HttpResponse response = httpClient.execute(getRequest);
      
      if (response.getStatusLine().getStatusCode() != 200) {
        terminal.printError("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
      }
      else{

        terminal.printLog("Request complete");

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        StringBuilder sb = new StringBuilder();
        
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        String json = sb.toString();

        respond = json;
        this.url = url;
        timestamp = Instant.now();

        saveToCache();

        terminal.printLog("Json data cached");
      };
    } catch (Exception e){
      terminal.printError("Request error: " + e.getMessage());
      e.printStackTrace();

      this.url = null;
      respond = null;
    };

    return respond;
  };

  private String generateHash(String text){
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hashBytes = md.digest(text.getBytes());

      StringBuilder sb = new StringBuilder();
      for (byte b : hashBytes) {
          sb.append(String.format("%02x", b));
      };
      
      terminal.printLog("Hash Created");

      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      terminal.printError("MD5 algorithm not found: " + e);
      return null;
    }
  };

  private void saveToCache() {
    if(respond == null || url == null) {
      terminal.printWarning("Nothing to cache");
      return;
    };

    File filename = new File(Config.LOGFILEPATH + File.separator + "cache" + File.separator +  generateHash(url) + ".json");
    
    File parentDir = filename.getParentFile();
    if (!parentDir.exists()) parentDir.mkdirs();

    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      ApiCache cache = new ApiCache(timestamp, respond);
      mapper.writerWithDefaultPrettyPrinter().writeValue(filename, cache);
      terminal.printLog("Respond is cached");
    } catch (Exception e) {
      terminal.printError("when save to JSON: " + e);
      e.printStackTrace();
    }
  };

  private String loadFromCache(String url) {
    if(url == null){
      terminal.printWarning("Url is null");
      return null;
    };

    File filename = new File(Config.LOGFILEPATH + File.separator + "cache" + File.separator + generateHash(url) + ".json");
    
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      ApiCache cache = mapper.readValue(filename, ApiCache.class);

      this.timestamp = cache.getTimestamp();
      this.respond = cache.getData();
      terminal.printLog("Cache loaded");

      return respond;
    } catch (Exception e) {
      terminal.printWarning("Cache not found or invalid: " + e.getMessage());
      return null;
    }
  };

  public String get(String url){
    return request(url);
  };

};