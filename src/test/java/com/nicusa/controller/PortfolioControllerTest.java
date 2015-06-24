package com.nicusa.controller;

import com.nicusa.assembler.DrugAssembler;
import com.nicusa.assembler.PortfolioAssembler;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.PortfolioResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioControllerTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private PortfolioAssembler portfolioAssembler;

    @InjectMocks
    private PortfolioController portfolioController;

    @Test
    public void testGetPortfolio() throws Exception {
        Portfolio portfolio = new Portfolio();
        when(entityManager.find(Portfolio.class, 1L)).thenReturn(portfolio);
        PortfolioResource portfolioResource = new PortfolioResource();
        when(portfolioAssembler.toResource(portfolio)).thenReturn(portfolioResource);
        ResponseEntity<PortfolioResource> portfolioResourceResponseEntity = portfolioController.getPortfolio(1L);
        assertThat(HttpStatus.OK, is(portfolioResourceResponseEntity.getStatusCode()));
        assertThat(portfolioResource, is(portfolioResourceResponseEntity.getBody()));
    }

    @Test
    public void testGetPortfolioNotFound() {
        when(entityManager.find(Portfolio.class, 1L)).thenReturn(null);
        ResponseEntity<PortfolioResource> portfolioResourceResponseEntity = portfolioController.getPortfolio(1L);
        assertThat(HttpStatus.NOT_FOUND, is(portfolioResourceResponseEntity.getStatusCode()));
        assertThat(portfolioResourceResponseEntity.getBody(), is(nullValue()));
    }
}