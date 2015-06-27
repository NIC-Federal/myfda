package com.nicusa.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdverseEffectTest {

  @Test
  public void effectTest () {
    AdverseEffect ae = new AdverseEffect();
    ae.setEffect( "heykitty" );
    assertEquals( "heykitty", ae.getEffect() );
  }

  @Test
  public void countTest () {
    AdverseEffect ae = new AdverseEffect();
    ae.setCount( 420L );
    assertEquals( new Long( 420L ), ae.getCount() );
  }

  @Test
  public void totalTest () {
    AdverseEffect ae = new AdverseEffect();
    ae.setTotal( 20L );
    assertEquals( new Long( 20L ), ae.getTotal() );
  }

  @Test
  public void descriptionTest () {
    AdverseEffect ae = new AdverseEffect();
    ae.setDescription( "I hate candy" );
    assertEquals( "I hate candy", ae.getDescription() );
  }

  @Test
  public void nullHashTest () {
    AdverseEffect ae = new AdverseEffect();
    assertEquals( ae.hashCode(), ae.hashCode() );
  }

  @Test
  public void nullCompareTest () {
    AdverseEffect a = new AdverseEffect();
    AdverseEffect b = new AdverseEffect();
    assertEquals( 0, a.compareTo( b ));
  }

  @Test
  public void nullEqualsTest () {
    AdverseEffect a = new AdverseEffect();
    AdverseEffect b = new AdverseEffect();
    assertEquals( a, b );
  }

    
}
