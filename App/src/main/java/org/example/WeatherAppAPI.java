package org.example;

import org.example.Terminal;
import org.example.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;




public class WeatherAppAPI {
  Terminal terminal = new Terminal();
  private static String respond = "";


  private void request() {
    try(CloseableHttpClient httpClient = HttpClients.createDefault()){
      HttpGet getRequest = new HttpGet(Config.REQUESTURL);
            
      getRequest.addHeader("accept", "application/json");
      
      terminal.printLog("Sending request");
      HttpResponse response = httpClient.execute(getRequest);
      
      if (response.getStatusLine().getStatusCode() != 200) {
        terminal.printError("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
      }
      else{

        terminal.printLog("Request ended successfully");

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        StringBuilder sb = new StringBuilder();
        
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        String json = sb.toString();
        respond = json;

        terminal.printLog("Json data cached");
      };
    } catch (Exception e){
      terminal.printError("Request error: " + e.getMessage());
      e.printStackTrace();
      respond = "";
    };
  };

  public String get(){
    if(respond == "") {
      terminal.printLog("Request data from URL");
      request();
      terminal.printLog("Request finished");
    }
    else{
      terminal.printLog("Used cached request");
    };

    return respond;
  };

};