package com.nicusa.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NotificationTypeAttributeConverter implements AttributeConverter<NotificationType, String> {
  @Override
  public String convertToDatabaseColumn(NotificationType notificationType) {
    if (notificationType == null) {
      return null;
    }
    return notificationType.name();
  }

  @Override
  public NotificationType convertToEntityAttribute(String s) {
    if (s == null) {
      return null;
    }
    for (NotificationType notificationType : NotificationType.values()) {
      if (notificationType.name().equals(s)) {
        return notificationType;
      }
    }
    return null;
  }
}
