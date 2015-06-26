package com.nicusa.controller;

import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class SecurityControllerTest {

  @InjectMocks
  private SecurityController securityController;

  @Mock
  private EntityManager entityManager;

  @Before
  public void before() {
    SecurityContextHolder.getContext().setAuthentication(null);
  }

  @After
  public void after() {
    SecurityContextHolder.getContext().setAuthentication(null);
  }


  @Test
  public void testSignin()  {
    UserProfile userProfile = new UserProfile();
    userProfile.setId(1L);
    securityController.signin(userProfile);
    assertCorrectPrincipalSetup();
  }

  private void assertCorrectPrincipalSetup() {
    assertThat(SecurityContextHolder.getContext().getAuthentication(), is(not(nullValue())));
    assertThat(SecurityContextHolder.getContext().getAuthentication(),
      instanceOf(UsernamePasswordAuthenticationToken.class));
    assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), is(not(nullValue())));
    assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), instanceOf(Long.class));
    assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), is(1L));
  }

  @Test
  public void testGetAuthenticatedUserProfileId() {
    UserProfile userProfile = new UserProfile();
    userProfile.setId(1L);
    securityController.signin(userProfile);
    assertThat(securityController.getAuthenticatedUserProfileId(), is(1L));
  }

  @Test
  public void testGetAuthenticateUserProfileIdAnonymous() {
    assertThat(securityController.getAuthenticatedUserProfileId(), is(UserProfileResource.ANONYMOUS_USER_PROFILE_ID));
  }

  @Test
  public void testSignupNoArgs() throws Exception {
    assertThat(securityController.signin(), is("signin"));
  }

  @Test
  public void testSignupWithWebRequest() {
    WebRequest webRequest = mock(WebRequest.class);
    assertThat(securityController.signup(webRequest), is("redirect:/"));
  }
}
