package com.nicusa.controller;

import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.RequestContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class SecurityController {


  public static final String LOGGED_IN_USER_PROFILE_ID = "LOGGED_IN_USER_PROFILE_ID";

  @PersistenceContext
  private EntityManager entityManager;

  private ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String signin() {
    return "signin";
  }

  @RequestMapping(value="/signup", method= RequestMethod.GET)
  @Transactional
  public String signup(WebRequest request) {
    UserProfile userProfile = new UserProfile();
    userProfile.setUserId(UUID.randomUUID().toString());
    entityManager.persist(userProfile);

    signin(userProfile);

    providerSignInUtils.doPostSignUp(userProfile.getUserId(), request);
    return "redirect:/";
  }

  public void signin(UserProfile userProfile) {
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userProfile.getId(), null, null));
  }

  public Long getAuthenticatedUserProfileId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication instanceof AnonymousAuthenticationToken) {
      return UserProfileResource.ANONYMOUS_USER_PROFILE_ID;
    } else {
      return (Long)authentication.getCredentials();
    }
  }

}
