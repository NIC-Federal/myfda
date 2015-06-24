package com.nicusa.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class UserProfileResource extends ResourceSupport {

  private String userId;
  private String name;
  private String emailAddress;
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
}
