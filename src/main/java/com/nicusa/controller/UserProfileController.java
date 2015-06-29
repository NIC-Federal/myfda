package com.nicusa.controller;

import com.nicusa.assembler.UserProfileAssembler;
import com.nicusa.converter.UserProfileResourceToDomainConverter;
import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserProfileController {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private UserProfileAssembler userProfileAssembler;

  @Autowired
  private SecurityController securityController;

  @Autowired
  private UserProfileResourceToDomainConverter userProfileResourceToDomainConverter;

  @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<?> getUser() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(linkTo(methodOn(UserProfileController.class).getUserProfile(securityController
      .getAuthenticatedUserProfileId())).toUri());
    return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
  }

  @ResponseBody
  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<Map<String,UserProfileResource>> getUserProfile(@PathVariable("id") Long id) {
    Map<String,UserProfileResource> userProfileResourceMap = new HashMap<>();
    if(id == UserProfileResource.ANONYMOUS_USER_PROFILE_ID) {
      userProfileResourceMap.put("user", UserProfileResource.ANONYMOUS_USER_PROFILE);
      return new ResponseEntity<>(userProfileResourceMap, HttpStatus.OK);
    } else {
      Long loggedInUserProfileId = securityController.getAuthenticatedUserProfileId();
      if (loggedInUserProfileId != null && loggedInUserProfileId.equals(id)) {
        UserProfile userProfile = entityManager.find(UserProfile.class, id);
        if (userProfile == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
          userProfileResourceMap.put("user", userProfileAssembler.toResource(userProfile));
          return new ResponseEntity<>(userProfileResourceMap, HttpStatus.OK);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }
  }

  @ResponseBody
  @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<?> createUserProfile(@RequestBody UserProfileResource userProfileResource) {
    UserProfile userProfile = userProfileResourceToDomainConverter.convert(userProfileResource);
    if(securityController.getAuthenticatedUserProfileId() != userProfile.getId() ||
       securityController.getAuthenticatedUserProfileId() == UserProfileResource.ANONYMOUS_USER_PROFILE_ID) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } else {
      entityManager.persist(userProfile);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(linkTo(methodOn(UserProfileController.class).getUserProfile(userProfile.getId())).toUri());
      return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
  }

}
