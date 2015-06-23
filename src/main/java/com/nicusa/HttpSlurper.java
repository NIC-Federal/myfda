package com.nicusa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpSlurper {

  public String getData ( String query ) {
    String result = "";
    try {
      String charset = StandardCharsets.UTF_8.name();
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
    return result;
  }

}
