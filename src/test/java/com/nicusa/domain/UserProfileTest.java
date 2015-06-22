package com.nicusa.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserProfileTest {

    @Test
    public void getIdShouldReturnValuePassedInSetId() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        assertThat(userProfile.getId(), is(1L));
    }

    @Test
    public void getNameShouldReturnValuePassedInSetName() {
        UserProfile userProfile = new UserProfile();
        userProfile.setName("Unkitty");
        assertThat(userProfile.getName(), is("Unkitty"));
    }
}