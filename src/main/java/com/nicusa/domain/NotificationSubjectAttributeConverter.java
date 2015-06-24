package com.nicusa.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NotificationSubjectAttributeConverter implements AttributeConverter<NotificationSubject, String> {

  @Override
  public String convertToDatabaseColumn(NotificationSubject notificationSubject) {
    if (notificationSubject == null) {
      return null;
    }
    return notificationSubject.name();
  }

  @Override
  public NotificationSubject convertToEntityAttribute(String s) {
    if (s == null) {
      return null;
    }
    for (NotificationSubject notificationSubject : NotificationSubject.values()) {
      if (notificationSubject.name().equals(s)) {
        return notificationSubject;
      }
    }
    return null;
  }
}
