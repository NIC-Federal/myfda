package com.nicusa;

import static org.junit.Assert.*;
import org.junit.Test;

public class HttpSlurperTest {

  @Test
  public void checkSimpleSlurp() {
    HttpSlurper slurp = new HttpSlurper();
    String data = slurp.getData(
        "http://httpbin.org/get?thestuff=therealstuff" );
    assertTrue( data.contains( "therealstuff"  ));
  }
}
