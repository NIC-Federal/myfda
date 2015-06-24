package com.nicusa.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class UserProfileTest {

    private UserProfile userProfile;

    @Before
    public void before() {
        userProfile = new UserProfile();
    }

    @Test
    public void getIdShouldReturnValuePassedInSetId() {
        assertThat(userProfile.getId(), is(nullValue()));
        userProfile.setId(1L);
        assertThat(userProfile.getId(), is(1L));
    }

    @Test
    public void getNameShouldReturnValuePassedInSetName() {
        assertThat(userProfile.getName(), is(nullValue()));
        userProfile.setName("Unkitty");
        assertThat(userProfile.getName(), is("Unkitty"));
    }

    @Test
    public void testGetUserId() throws Exception {
        assertThat(userProfile.getUserId(), is(nullValue()));
        userProfile.setUserId("unikitty");
        assertThat(userProfile.getUserId(), is("unikitty"));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(userProfile.getName(), is(nullValue()));
        userProfile.setName("unikitty");
        assertThat(userProfile.getName(), is("unikitty"));
    }

    @Test
    public void testGetEmailAddress() throws Exception {
        assertThat(userProfile.getEmailAddress(), is(nullValue()));
        userProfile.setEmailAddress("unkitty@cloudcuckopalace.gov");
        assertThat(userProfile.getEmailAddress(), is("unkitty@cloudcuckopalace.gov"));
    }

    @Test
    public void testGetPortfolio() throws Exception {
        assertThat(userProfile.getPortfolio(), is(nullValue()));
        Portfolio portfolio = new Portfolio();
        userProfile.setPortfolio(portfolio);
        assertThat(userProfile.getPortfolio(), is(portfolio));
    }

    @Test
    public void testGetNotificationSettings() throws Exception {
        assertThat(userProfile.getNotificationSettings(), is(nullValue()));
        Collection<NotificationSetting> notificationSettings = new ArrayList<>();
        userProfile.setNotificationSettings(notificationSettings);
        assertThat(userProfile.getNotificationSettings(), is(notificationSettings));
    }
}