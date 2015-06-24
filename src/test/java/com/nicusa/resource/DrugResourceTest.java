package com.nicusa.resource;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class DrugResourceTest {

    @Test
    public void getNameShouldShouldReturnValuePassedInSetName() {
        DrugResource drugResource = new DrugResource();
        assertThat(drugResource.getName(), is(nullValue()));
        drugResource.setName("unikitty");
        assertThat(drugResource.getName(), is("unikitty"));
    }
}