package com.nicusa.util;

import java.util.Set;

public class DrugSearchResult {
  private String unii;
  private String brandName;
  private String genericName;
  private Long rxcui;
  private Set<String> activeIngredients;

  public String getUnii () {
    return this.unii;
  }
  public void setUnii ( String u ) {
    if ( u != null ) {
      u = u.toUpperCase();
    }
    this.unii = u;
  }
  public String getBrandName () {
    return this.brandName;
  }
  public void setBrandName ( String b ) {
    this.brandName = b;
  }
  public String getGenericName () {
    return this.genericName;
  }
  public void setGenericName ( String g ) {
    this.genericName = g;
  }
  public Long getRxcui () {
    return this.rxcui;
  }
  public void setRxcui ( Long r ) {
    this.rxcui = r;
  }
  public Set<String> getActiveIngredients () {
    return this.activeIngredients;
  }
  public void setActiveIngredients ( Set<String> a ) {
    this.activeIngredients = a;
  }

}
