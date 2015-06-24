package com.nicusa.domain;

import javax.persistence.*;

@Entity
public class NotificationSetting {

  private static final String SEQUENCE_NAME = "NOTIFICATION_SETTING_SEQUENCE";

  private Long id;
  private NotificationType notificationType;
  private NotificationSubject notificationSubject;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Convert(converter = NotificationTypeAttributeConverter.class)
  public NotificationType getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(NotificationType notificationType) {
    this.notificationType = notificationType;
  }

  @Convert(converter = NotificationSubjectAttributeConverter.class)
  public NotificationSubject getNotificationSubject() {
    return notificationSubject;
  }

  public void setNotificationSubject(NotificationSubject notificationSubject) {
    this.notificationSubject = notificationSubject;
  }


}
