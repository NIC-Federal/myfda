package com.nicusa;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UiApplication {

    private static final Logger log = LoggerFactory.getLogger(UiApplication.class);
    HttpSlurper slurp = new HttpSlurper();

    @RequestMapping("/resource")
    public Map<String,Object> home() {
      Map<String,Object> model = new HashMap<>();
      model.put("id", UUID.randomUUID().toString());
      model.put("content", "Hello World");
      return model;
    }

    @RequestMapping("/drug")
    public String search(
        @RequestParam(value="name", defaultValue="") String name,
        @RequestParam(value="limit", defaultValue="10") int limit,
        @RequestParam(value="skip", defaultValue="0") int skip ) {
      if ( name == null ) {
        name = "";
      }

      try {
        String query = String.format(
            "https://api.fda.gov/drug/label.json?search=openfda.brand_name:%s&limit=%d&skip=%d",
            URLEncoder.encode( name, StandardCharsets.UTF_8.name() ),
            limit,
            skip );
        return slurp.getData( query );
      } catch ( UnsupportedEncodingException uee ) {
        log.error( "Help, UTF 8 encoding fail!", uee );
        throw new RuntimeException( uee );
      }
    }

    @RequestMapping("/autocomplete")
    public String autocomplete(
        @RequestParam(value="name", defaultValue="") String name) {
      if ( name == null ) {
        name = "";
      }

      String result = "[{\"value\":\"" + name + "\"}]";
      if ( name.length() >= 3 ) {
        try {
          String query = String.format(
              "https://dailymed.nlm.nih.gov/dailymed/autocomplete.cfm?key=search&returntype=json&term=%s",
              URLEncoder.encode( name, StandardCharsets.UTF_8.name() ));
          result = slurp.getData( query );
        } catch ( UnsupportedEncodingException uee ) {
          log.error( "Help, UTF 8 encoding failed!", uee );
        }
      }
      return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
