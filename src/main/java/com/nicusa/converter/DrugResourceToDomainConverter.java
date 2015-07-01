package com.nicusa.converter;

import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DrugResourceToDomainConverter extends ResourceToDomainConverter<DrugResource, Drug> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Drug convert(DrugResource drugResource) {
    Drug drug;
    if (drugResource.getId() != null) {
      drug = entityManager.find(Drug.class, drugResource.getId());
    } else {
      drug = new Drug();
    }
    drug.setName(drugResource.getName());
    drug.setUnii(drugResource.getUnii());
    return drug;
  }

}
