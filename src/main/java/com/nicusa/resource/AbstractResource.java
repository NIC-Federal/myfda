package com.nicusa.resource;

import java.util.HashMap;
import java.util.Map;

public class AbstractResource {

  private Long id;
  private Map<String,Object> links = new HashMap<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Map<String, Object> getLinks() {
    return links;
  }

  public void setLinks(Map<String, Object> links) {
    this.links = links;
  }

}
