package com.nicusa.assembler;

import com.nicusa.domain.Drug;
import com.nicusa.domain.Portfolio;
import com.nicusa.resource.DrugResource;
import com.nicusa.resource.PortfolioResource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioAssemblerTest {

    @InjectMocks
    private PortfolioAssembler portfolioAssembler;

    @Mock
    private DrugAssembler drugAssembler;

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

        DrugResource drugResource1 = new DrugResource();
        DrugResource drugResource2 = new DrugResource();

        when(drugAssembler.toResource(drug1)).thenReturn(drugResource1);
        when(drugAssembler.toResource(drug2)).thenReturn(drugResource2);

        PortfolioResource portfolioResource = portfolioAssembler.toResource(portfolio);

        Integer linkCount = 0;
        assertThat(portfolioResource.getDrugResources().size(), is(2));

    }
}
