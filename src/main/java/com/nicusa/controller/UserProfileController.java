package com.nicusa.controller;

import com.nicusa.assembler.UserProfileAssembler;
import com.nicusa.converter.UserProfileResourceToDomainConverter;
import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserProfileController {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private UserProfileAssembler userProfileAssembler;

  @Autowired
  private UserProfileResourceToDomainConverter userProfileResourceToDomainConverter;

  @ResponseBody
  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/hal+json")
  public ResponseEntity<UserProfileResource> getUserProfile(@AuthenticationPrincipal Principal principal,
                                                            @PathVariable("id") Long id) {
    UserProfile userProfile = entityManager.find(UserProfile.class, id);
    if (userProfile == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(userProfileAssembler.toResource(userProfile), HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/hal+json")
  public ResponseEntity<?> createUserProfile(@AuthenticationPrincipal Principal principal,
                                             @RequestBody UserProfileResource userProfileResource) {

    UserProfile userProfile = userProfileResourceToDomainConverter.convert(userProfileResource);
    entityManager.persist(userProfile);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(linkTo(methodOn(UserProfileController.class).getUserProfile(principal,
      userProfile.getId())).toUri());
    return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
  }

}
