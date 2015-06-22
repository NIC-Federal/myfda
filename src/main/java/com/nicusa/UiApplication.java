package com.nicusa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UiApplication {

    @RequestMapping("/resource")
    public Map<String,Object> home() {
      Map<String,Object> model = new HashMap<String,Object>();
      model.put("id", UUID.randomUUID().toString());
      model.put("content", "Hello World");
      return model;
    }

    @RequestMapping("/autocomplete")
    public Map<String,Object> autocomplete(
        @RequestParam(value="name", defaultValue="") String name) {
      if ( name == null ) {
        name = "";
      }

      String result = name;
      if ( name.length() >= 3 ) {
        try {
          String charset = StandardCharsets.UTF_8.name();
          String query = String.format(
              "https://dailymed.nlm.nih.gov/dailymed/autocomplete.cfm?key=search&returntype=json&term=%s",
              URLEncoder.encode( name, charset ));
          URLConnection conn = new URL( query ).openConnection();
          conn.setRequestProperty( "Accept-Charset", charset );

          BufferedReader in = new BufferedReader(
              new InputStreamReader(
                conn.getInputStream(), charset ));

          StringBuilder sb = new StringBuilder();
          for ( String line; (line = in.readLine()) != null; ) {
            sb.append( line );
          } 
          result = sb.toString().trim();
        } catch ( IOException ioe ) {
          // TODO LOGME
          System.err.println( ioe );
        }
      }


      Map<String,Object> model = new HashMap<String,Object>();
      model.put( "result", result );
      return model;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
