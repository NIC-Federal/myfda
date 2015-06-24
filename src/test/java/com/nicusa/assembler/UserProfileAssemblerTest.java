package com.nicusa.assembler;

import com.nicusa.domain.Portfolio;
import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by mchurch on 6/23/15.
 */
public class UserProfileAssemblerTest {

    @Test
    public void testToResource() throws Exception {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        UserProfileAssembler userProfileAssembler = new UserProfileAssembler();
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setName("unkitty");
        UserProfileResource userProfileResource = userProfileAssembler.toResource(userProfile);
        assertThat(userProfileResource.getName(), is(userProfile.getName()));
    }

    @Test
    public void testToResourceWithPortfolio() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        UserProfileAssembler userProfileAssembler = new UserProfileAssembler();
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setName("unikitty");
        Portfolio portfolio = new Portfolio();
        portfolio.setId(1L);
        userProfile.setPortfolio(portfolio);
        UserProfileResource userProfileResource = userProfileAssembler.toResource(userProfile);
        assertThat(userProfileResource.getName(), is(userProfile.getName()));
        assertThat(userProfileResource.getLink("portfolio"), is(not(nullValue())));
    }
}