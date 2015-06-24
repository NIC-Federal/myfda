package com.nicusa.assembler;

import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class DrugAssemblerTest {

    @Test
    public void testToResource() throws Exception {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        Drug drug = new Drug();
        drug.setId(1L);
        drug.setName("unikitty");
        DrugAssembler drugAssembler = new DrugAssembler();
        DrugResource drugResource = drugAssembler.toResource(drug);
        assertThat(drugResource.getName(), is(drug.getName()));
    }



}