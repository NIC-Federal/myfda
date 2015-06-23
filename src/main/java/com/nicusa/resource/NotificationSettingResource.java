package com.nicusa.resource;

import com.nicusa.domain.NotificationSubject;
import com.nicusa.domain.NotificationType;

/**
 * Created by mchurch on 6/23/15.
 */
public class NotificationSettingResource {

    private NotificationType notificationType;
    private NotificationSubject notificationSubject;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationSubject getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(NotificationSubject notificationSubject) {
        this.notificationSubject = notificationSubject;
    }
}
