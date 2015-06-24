package com.nicusa.resource;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class NotificationSettingResourceTest {

    @Test
    public void getNotificationTypeResourceShouldReturnValuePassedInSetNotificationTypeResource() {
        NotificationSettingResource notificationSettingResource = new NotificationSettingResource();
        assertThat(notificationSettingResource.getNotificationTypeResource(), is(nullValue()));
        notificationSettingResource.setNotificationTypeResource(NotificationTypeResource.ALL);
    }

    @Test
    public void getNotificationSubjectResourceShouldReturnValuePassedInSetNotificationSubjectResource() {
        NotificationSettingResource notificationSettingResource = new NotificationSettingResource();
        assertThat(notificationSettingResource.getNotificationSubjectResource(), is(nullValue()));
        notificationSettingResource.setNotificationSubjectResource(NotificationSubjectResource.RECALL);
        assertThat(notificationSettingResource.getNotificationSubjectResource(),
                is(NotificationSubjectResource.RECALL));
    }
}