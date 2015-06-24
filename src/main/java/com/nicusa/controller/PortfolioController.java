package com.nicusa.controller;

import com.nicusa.assembler.PortfolioAssembler;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.PortfolioResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RestController
public class PortfolioController {

  @PersistenceContext
  public EntityManager entityManager;

  @Autowired
  public PortfolioAssembler portfolioAssembler;


  @ResponseBody
  @RequestMapping(value = "/portfolio/{id}", method = RequestMethod.GET, produces = "application/hal+json")
  public ResponseEntity<PortfolioResource> getPortfolio(@PathVariable("id") Long id) {
    Portfolio portfolio = entityManager.find(Portfolio.class, id);
    if (portfolio == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(portfolioAssembler.toResource(portfolio), HttpStatus.OK);
  }
}
