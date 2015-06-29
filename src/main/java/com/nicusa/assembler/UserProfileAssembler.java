package com.nicusa.assembler;

import com.nicusa.controller.PortfolioController;
import com.nicusa.controller.UserProfileController;
import com.nicusa.domain.UserProfile;
import com.nicusa.resource.UserProfileResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserProfileAssembler {

  public UserProfileResource toResource(UserProfile userProfile) {
    UserProfileResource userProfileResource = new UserProfileResource();
    userProfileResource.setId(userProfile.getId());
    userProfileResource.setName(userProfile.getName());
    userProfileResource.setEmailAddress(userProfile.getEmailAddress());
    userProfileResource.setUserId(userProfile.getUserId());
    userProfileResource.setId(userProfile.getId());
    if (userProfile.getPortfolio() != null) {
      userProfileResource.getLinks().put("portfolio", linkTo(methodOn(PortfolioController.class).getPortfolio(userProfile.getPortfolio()
        .getId())).withRel("portfolio").getHref());
    }
    return userProfileResource;
  }
}
