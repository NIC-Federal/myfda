package com.nicusa.assembler;

import com.nicusa.controller.DrugController;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DrugAssembler {
  public DrugResource toResource(Drug drug) {
    DrugResource drugResource = new DrugResource();
    drugResource.setName(drug.getName());
    drugResource.setUnii(drug.getUnii());
    drugResource.setId(drug.getId());
    return drugResource;
  }
}
