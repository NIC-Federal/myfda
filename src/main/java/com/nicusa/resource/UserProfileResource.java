package com.nicusa.resource;

import com.nicusa.controller.UserProfileController;

import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserProfileResource extends AbstractResource {

  public static final UserProfileResource ANONYMOUS_USER_PROFILE;
  public static final Long ANONYMOUS_USER_PROFILE_ID = 0L;

  static {
    ANONYMOUS_USER_PROFILE = new UserProfileResource();
    ANONYMOUS_USER_PROFILE.setName("anonymous");
    ANONYMOUS_USER_PROFILE.setAnonymous(true);
    ANONYMOUS_USER_PROFILE.getLinks().put("self", linkTo(methodOn(UserProfileController.class).getUserProfile(0L))
      .withSelfRel().getHref());
  }

  private String userId;
  private String name;
  private String emailAddress;
  private Boolean anonymous = false;
  private Collection<NotificationSettingResource> notificationSettingResources;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Collection<NotificationSettingResource> getNotificationSettingResources() {
    return notificationSettingResources;
  }

  public void setNotificationSettingResources(Collection<NotificationSettingResource> notificationSettingResources) {
    this.notificationSettingResources = notificationSettingResources;
  }

  public Boolean getAnonymous() {
    return anonymous;
  }

  public void setAnonymous(Boolean anonymous) {
    this.anonymous = anonymous;
  }
}
