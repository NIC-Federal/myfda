package com.nicusa.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicusa.domain.Drug;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Collection;

public class PortfolioResource extends AbstractResource {

  private String name;
  private Collection<DrugResource> drugResources = new ArrayList<DrugResource>();

  public PortfolioResource() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<DrugResource> getDrugResources() {
    return drugResources;
  }

  public void setDrugResources(Collection<DrugResource> drugResources) {
    this.drugResources = drugResources;
  }

}
