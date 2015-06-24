package com.nicusa.controller;

import com.nicusa.assembler.DrugAssembler;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DrugControllerTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private DrugAssembler drugAssembler;

    @InjectMocks
    private DrugController drugController;

    @Test
    public void testGetDrugFound() {
        Drug persistedDrug = new Drug();
        persistedDrug.setId(1L);
        DrugResource drugResource = new DrugResource();
        when(entityManager.find(Drug.class, 1L)).thenReturn(persistedDrug);
        when(drugAssembler.toResource(persistedDrug)).thenReturn(drugResource);
        ResponseEntity<DrugResource> drugResourceResponseEntity = drugController.getDrug(1L);
        assertThat(HttpStatus.OK, is(drugResourceResponseEntity.getStatusCode()));
        assertThat(drugResourceResponseEntity.getBody(), is(drugResource));
    }

    @Test
    public void testGetDrugNotFound() {
        when(entityManager.find(Drug.class, 1L)).thenReturn(null);
        ResponseEntity<DrugResource> drugResourceResponseEntity = drugController.getDrug(1L);
        assertThat(HttpStatus.NOT_FOUND, is(drugResourceResponseEntity.getStatusCode()));
        assertThat(drugResourceResponseEntity.getBody(), is(nullValue()));
    }
}