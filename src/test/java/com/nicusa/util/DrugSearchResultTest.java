package com.nicusa.util;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;

public class DrugSearchResultTest {
  

  @Test
  public void uniiTest () {
    DrugSearchResult r = new DrugSearchResult();
    r.setUnii( "abcde12345" );
    assertEquals( "ABCDE12345", r.getUnii() );
  }

  @Test
  public void brandNameTest () {
    DrugSearchResult r = new DrugSearchResult();
    r.setBrandName( "jackson" );
    assertEquals( "jackson", r.getBrandName() );
  }

  @Test
  public void genericNameTest () {
    DrugSearchResult r = new DrugSearchResult();
    r.setGenericName( "unikitty" );
    assertEquals( "unikitty", r.getGenericName() );
  }

  @Test
  public void rxcuiTest () {
    DrugSearchResult r = new DrugSearchResult();
    r.setRxcui( 1L );
    assertEquals( new Long( 1L ), r.getRxcui() );
  }

  @Test
  public void activeIngredientsTest () {
    DrugSearchResult r = new DrugSearchResult();
    Set<String> in = new HashSet<String>();
    in.add( "blah" );
    in.add( "silly" );
    r.setActiveIngredients( in );
    assertEquals( 2, r.getActiveIngredients().size() );
    assertTrue( r.getActiveIngredients().contains( "silly" ));
    assertFalse( r.getActiveIngredients().contains( "unikitty" ));
  }
    
}
