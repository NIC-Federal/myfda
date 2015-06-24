package com.nicusa.resource;

public class NotificationSettingResource {

  private NotificationTypeResource notificationTypeResource;
  private NotificationSubjectResource notificationSubjectResource;

  public NotificationTypeResource getNotificationTypeResource() {
    return notificationTypeResource;
  }

  public void setNotificationTypeResource(NotificationTypeResource notificationTypeResource) {
    this.notificationTypeResource = notificationTypeResource;
  }

  public NotificationSubjectResource getNotificationSubjectResource() {
    return notificationSubjectResource;
  }

  public void setNotificationSubjectResource(NotificationSubjectResource notificationSubjectResource) {
    this.notificationSubjectResource = notificationSubjectResource;
  }
}
