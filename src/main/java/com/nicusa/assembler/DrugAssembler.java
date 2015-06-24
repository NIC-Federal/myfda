package com.nicusa.assembler;

import com.nicusa.controller.DrugController;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class DrugAssembler extends ResourceAssemblerSupport<Drug, DrugResource> {

  public DrugAssembler() {
    super(DrugController.class, DrugResource.class);
  }

  @Override
  public DrugResource toResource(Drug drug) {
    DrugResource drugResource = createResourceWithId(drug.getId(), drug);
    drugResource.setName(drug.getName());
    return drugResource;
  }
}
