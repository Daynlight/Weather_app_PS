package org.example;

import org.example.Terminal;
import org.example.JsonParser;
import org.example.StatsData;

import java.util.List;
import java.io.File;
import kafka.model.Root;

import org.fusesource.jansi.Ansi.Color;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;





class Statistics{
  private JsonParser json = new JsonParser();
  private Terminal terminal = new Terminal();
  String json_data = null;


  public Statistics(String json_data){
    this.json_data = json_data;
  };

  public void print(){
    terminal.printLog("Printing Statistics");
    
    terminal.print("Average Temperature: " + avgTemp());
    terminal.print("High Temperature > 25: " + highTemp(25));
    terminal.print("Average humidity: " + avgHumidity());
    terminal.print("High PM > 25: " + highPM(25));
  };

  private double avgTemp(){
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return 0.0;
    };

    double avgTemp = json.getRootsFromApi(json_data).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("temperature"))
      .mapToDouble(sd -> Double.parseDouble(sd.getValue()))
      .average()
      .orElse(0.0);
    
    return avgTemp;
  };
  
  private long highTemp(double value) {
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return 0;
    };

    long highTempCount = json.getRootsFromApi(json_data).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("temperature") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?") && 
        Double.parseDouble(sd.getValue()) > value)
      .count();

    return highTempCount;
  };

  private long highPM(double value){
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return 0;
    };

    long highPmCount = json.getRootsFromApi(json_data).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("P2") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?") && 
        Double.parseDouble(sd.getValue()) > value)
      .count();
      
    return highPmCount;
  };

  private double avgHumidity() {
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return 0.0;
    };

    double avgHumid =  json.getRootsFromApi(json_data).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("humidity") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?"))
      .mapToDouble(sd -> Double.parseDouble(sd.getValue()))
      .average()
      .orElse(0.0);

    return avgHumid;
  };

  public void saveAsXml(String filename) {
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return;
    };

    try{
      XmlMapper xmlMapper = new XmlMapper();
      StatsData stats = new StatsData(avgTemp(), highTemp(25), avgHumidity(), highPM(25));
      xmlMapper.writeValue(new File(filename), stats);
      terminal.printLog("XML is created");
    }
    catch (Exception e){
      terminal.printError("save to XML: " + e);
      e.printStackTrace();
    }
  };

  public void saveAsJson(String filename) {
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return;
    };

    try {
      ObjectMapper mapper = new ObjectMapper();
      StatsData stats = new StatsData(avgTemp(), highTemp(25), avgHumidity(), highPM(25));
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), stats);
      terminal.printLog("JSON is created");
    } catch (Exception e) {
      terminal.printError("when save to JSON: " + e);
      e.printStackTrace();
    }
  };

  public void saveAsPdf(String filename) {
    if(json_data == null) {
      terminal.printWarning("Json data is empty: " + json_data);
      return;
    };

    try {
      PdfWriter writer = new PdfWriter(filename);
      PdfDocument pdf = new PdfDocument(writer);
      Document document = new Document(pdf);
      document.add(new Paragraph("Average Temperature: " + avgTemp()));
      document.add(new Paragraph("High Temperature > 25: " + highTemp(25)));
      document.add(new Paragraph("Average Humidity: " + avgHumidity()));
      document.add(new Paragraph("High PM > 25: " + highPM(25)));
      document.close();
      terminal.printLog("PDF is created");
    } catch (Exception e) {
      terminal.printError("save to PDF: " + e);
      e.printStackTrace();
    }
  };

};