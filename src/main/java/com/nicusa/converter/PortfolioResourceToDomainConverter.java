package com.nicusa.converter;

import com.nicusa.controller.PortfolioController;
import com.nicusa.domain.Drug;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.DrugResource;
import com.nicusa.resource.PortfolioResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public class PortfolioResourceToDomainConverter extends ResourceToDomainConverter<PortfolioResource, Portfolio> {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private DrugResourceToDomainConverter drugResourceToDomainConverter;

  @Override
  public Portfolio convert(PortfolioResource portfolioResource) {
    Portfolio portfolio;
    if (portfolioResource.getId() != null) {
      portfolio = entityManager.find(Portfolio.class, portfolioResource.getId());
    } else {
      portfolio = new Portfolio();
    }
    Collection<Drug> drugs = new ArrayList<>();
    for(DrugResource drugResource : portfolioResource.getDrugResources()) {
      drugs.add(drugResourceToDomainConverter.convert(drugResource));
    }
    portfolio.setDrugs(drugs);
    return portfolio;

  }
}
