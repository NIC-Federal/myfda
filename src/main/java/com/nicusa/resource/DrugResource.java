package com.nicusa.resource;


public class DrugResource extends AbstractResource {

  private String name;
  private String unii;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUnii() {
    return unii;
  }
  public void setUnii(String unii) {
    this.unii = unii;
  }
}
