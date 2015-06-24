package com.nicusa.resource;

import com.nicusa.domain.NotificationSetting;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class UserProfileResourceTest {

    private UserProfileResource userProfileResource;

    @Before
    public void before() {
        userProfileResource = new UserProfileResource();
    }

    @Test
    public void getUserIdShouldReturnValuePassedInSetUserId() {
        assertThat(userProfileResource.getUserId(), is(nullValue()));
        userProfileResource.setUserId("unikitty");
        assertThat(userProfileResource.getUserId(), is("unikitty"));
    }

    @Test
    public void getNameShouldReturnValuePassedInSetName() {
        assertThat(userProfileResource.getName(), is(nullValue()));
        userProfileResource.setName("unikitty");
        assertThat(userProfileResource.getName(), is("unikitty"));
    }

    @Test
    public void getEmailAddressShouldReturnValuePassedInSetEmailAddress() {
        assertThat(userProfileResource.getEmailAddress(), is(nullValue()));
        userProfileResource.setEmailAddress("unkitty@cloudcuckoopalace.gov");
        assertThat(userProfileResource.getEmailAddress(), is("unkitty@cloudcuckoopalace.gov"));
    }

    @Test
    public void getNotifcationSettingsShouldReturnBaluePassedInSetNotificationSettings() {
        assertThat(userProfileResource.getNotificationSettingResources(), is(nullValue()));
        Collection<NotificationSettingResource> notificationSettingResources = new ArrayList<>();
        userProfileResource.setNotificationSettingResources(notificationSettingResources);
        assertThat(userProfileResource.getNotificationSettingResources(), is(notificationSettingResources));
    }

}