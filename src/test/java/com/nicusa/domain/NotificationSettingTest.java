package com.nicusa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class NotificationSettingTest {

    private NotificationSetting notificationSetting;

    @Before
    public void before() {
        notificationSetting = new NotificationSetting();
    }

    @Test
    public void getIdShouldReturnValuePassedInSetId() {
        assertThat(notificationSetting.getId(), is(nullValue()));
        notificationSetting.setId(1L);
        assertThat(notificationSetting.getId(), is(1L));
    }

    @Test
    public void getNotificationSubjectReturnValuePassedInSetNotificationSubject() {
        assertThat(notificationSetting.getNotificationSubject(), is(nullValue()));
        notificationSetting.setNotificationSubject(NotificationSubject.ADVERSE_EFFECTS);
        assertThat(notificationSetting.getNotificationSubject(), is(NotificationSubject.ADVERSE_EFFECTS));
    }

    @Test
    public void getNotificationTypeShouldReturnValuePassedInSetNotificationType() {
        assertThat(notificationSetting.getNotificationType(), is(nullValue()));
        notificationSetting.setNotificationType(NotificationType.ALL);
        assertThat(notificationSetting.getNotificationType(), is(NotificationType.ALL));
    }

}