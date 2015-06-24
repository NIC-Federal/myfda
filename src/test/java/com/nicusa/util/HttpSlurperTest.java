package com.nicusa.util;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

public class HttpSlurperTest {

  @Test
  public void checkSimpleSlurp() throws IOException {
    HttpSlurper slurp = new HttpSlurper();
    String data = slurp.getData(
        "http://httpbin.org/get?thestuff=therealstuff" );
    assertTrue( data.contains( "therealstuff"  ));
  }
}
