package com.nicusa.controller;

import com.nicusa.assembler.DrugAssembler;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RestController
@RequestMapping("/drug")
public class DrugController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DrugAssembler drugAssembler;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    public ResponseEntity<DrugResource> getDrug(@PathVariable("id") Long id) {
        Drug drug = entityManager.find(Drug.class, id);
        if(drug == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drugAssembler.toResource(drug), HttpStatus.OK);
    }

}