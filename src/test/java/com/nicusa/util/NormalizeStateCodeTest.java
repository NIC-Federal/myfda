package com.nicusa.util;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;

public class NormalizeStateCodeTest {

  @Test
  public void simpleStateTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "ID" );
    assertEquals( 1, res.size() );
    assertEquals( "ID", res.iterator().next().toString() );
  }

  @Test
  public void doubleStateTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "ID OR" );
    assertEquals( 2, res.size() );
    assertTrue( res.contains( NormalizeStateCode.IDAHO ));
    assertTrue( res.contains( NormalizeStateCode.OREGON ));
  }

  @Test
  public void junkStateTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "Unikitty rules" );
    assertEquals( 0, res.size() );
  }

  @Test
  public void nationwideTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "ID OR Nationwide" );
    assertEquals( 0, res.size() );
  }

  @Test
  public void unitedStatesTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "United States ID OR" );
    assertEquals( 0, res.size() );
  }

  @Test
  public void junkUnitedStatesTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "United ID States OR" );
    assertEquals( 2, res.size() );
    assertTrue( res.contains( NormalizeStateCode.IDAHO ));
    assertTrue( res.contains( NormalizeStateCode.OREGON ));
  }

  @Test
  public void fullStateTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "The recall effects Oregon and ID" );
    assertEquals( 2, res.size() );
    assertTrue( res.contains( NormalizeStateCode.IDAHO ));
    assertTrue( res.contains( NormalizeStateCode.OREGON ));
  }

  @Test
  public void twoWordStateTest () {
    Set<NormalizeStateCode> res = NormalizeStateCode.parse( "blah blah north carolina" );
    assertEquals( 1, res.size() );
    assertTrue( res.contains( NormalizeStateCode.NORTH_CAROLINA ));
  }

  @Test
  public void twoWordStateJsonTest () {
    String res = NormalizeStateCode.parseToJson( "blah blah north carolina" );
    assertTrue( res.contains( "NC" ));
  }

  @Test
  public void realExampleJsonTest () {
    String res = NormalizeStateCode.parseToJson( "Nationwide (& Puerto Rico)" );
    assertEquals( "[]", res );
  }

}
