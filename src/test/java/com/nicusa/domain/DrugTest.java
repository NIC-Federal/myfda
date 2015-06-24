package com.nicusa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class DrugTest {

    private Drug drug;

    @Before
    public void before() {
        drug = new Drug();
    }

    @Test
    public void testGetIdShouldReturnTheValuePassedInSetId() throws Exception {
        assertThat(drug.getId(), is(nullValue()));
        drug.setId(1L);
        assertThat(drug.getId(), is(1L));
    }

    @Test
    public void getNameShouldReturnTheValuePassedInSetName() throws Exception {
        assertThat(drug.getId(), is(nullValue()));
        drug.setName("unikitty");
        assertThat(drug.getName(), is("unikitty"));
    }
}