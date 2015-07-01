package com.nicusa.assembler;

import com.nicusa.controller.PortfolioController;
import com.nicusa.domain.Drug;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.PortfolioResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PortfolioAssembler {

  @Autowired
  public DrugAssembler drugAssembler;

  public PortfolioResource toResource(Portfolio portfolio) {
    PortfolioResource portfolioResource = new PortfolioResource();
    if (portfolio.getId() != null) {
      portfolioResource.setId(portfolio.getId());
      portfolioResource.getLinks().put("self", linkTo(methodOn(PortfolioController.class).getPortfolio(portfolio.getId())).withSelfRel().getHref());
    }
    portfolioResource.setName(portfolio.getName());
    portfolioResource.setId(portfolio.getId());

    for (Drug drug : portfolio.getDrugs()) {
      portfolioResource.getDrugResources().add(drugAssembler.toResource(drug));
    }
    return portfolioResource;
  }
}
