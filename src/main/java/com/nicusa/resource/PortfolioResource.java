package com.nicusa.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Collection;

public class PortfolioResource extends AbstractResource {

  private String name;

  public PortfolioResource() {
    super();
    getLinks().put("drugs", new ArrayList<String>());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonIgnore
  public Collection<String> getDrugLinks() {
    return (Collection<String>)getLinks().get("drugs");
  }

}
