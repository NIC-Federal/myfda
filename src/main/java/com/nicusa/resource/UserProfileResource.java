package com.nicusa.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class UserProfileResource extends ResourceSupport {

  public static final UserProfileResource ANONYMOUS_USER_PROFILE;
  public static final Long ANONYMOUS_USER_PROFILE_ID = 0L;

  static {
    ANONYMOUS_USER_PROFILE = new UserProfileResource();
    ANONYMOUS_USER_PROFILE.setName("anonymous");
    ANONYMOUS_USER_PROFILE.setAnonymous(true);
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
