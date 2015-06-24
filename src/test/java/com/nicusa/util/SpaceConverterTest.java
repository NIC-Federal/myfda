package com.nicusa.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class SpaceConverterTest {

  @Test
  public void checkConvert () {
    SpaceConverter sc = new SpaceConverter();
    String testMe = "blah blah  blah";
    assertEquals( sc.convert( testMe ), "blah+blah++blah" );
  }

  @Test
  public void checkNoConvert ( ) {
    SpaceConverter sc = new SpaceConverter();
    String testMe = "blahblahblah";
    assertEquals( sc.convert( testMe ), testMe );
  }

}
