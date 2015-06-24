package com.nicusa.util;

import java.util.Set;

public class DrugSearchResult {
  private String unii;
  private String brandName;
  private String genericName;
  private Long rxcui;
  private Set<String> activeIngrediants;

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
  public Set<String> getActiveIngrediants () {
    return this.activeIngrediants;
  }
  public void setActiveIngrediants ( Set<String> a ) {
    this.activeIngrediants = a;
  }

}
