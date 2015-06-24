package com.nicusa.assembler;

import com.nicusa.domain.Drug;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.PortfolioResource;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PortfolioAssemblerTest {

    @Test
    public void testToResource() throws Exception {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        Drug drug1 = new Drug();
        drug1.setId(1L);
        drug1.setName("name");
        Drug drug2 = new Drug();
        drug2.setId(2L);
        drug2.setName("name2");

        Collection<Drug> drugs = new ArrayList<>();
        drugs.add(drug1);
        drugs.add(drug2);
        Portfolio portfolio = new Portfolio();
        portfolio.setId(1L);
        portfolio.setDrugs(drugs);
        PortfolioAssembler portfolioAssembler = new PortfolioAssembler();
        PortfolioResource portfolioResource = portfolioAssembler.toResource(portfolio);

        Integer linkCount = 0;
        for(Link link : portfolioResource.getLinks()) {
            if(link.getRel().equals("drugs")) {
                linkCount++;
            }
        }
        assertThat(linkCount, is(2));

    }
}