package com.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.NewShare;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mithundas on 12/2/14.
 */

@Controller
public class LinkedInController {

    @Autowired
    ConnectionRepository connectionRepository;

    @RequestMapping("/connect/linkedin/profile")
    public String getLinkedInProfile(Model model){

        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
        System.out.println(connection);
        if(connection!=null) {
            //return "redirect:/auth/linkedin";

            LinkedIn linkedIn = connection.getApi();
            LinkedInProfile profile = linkedIn.profileOperations().getUserProfile();
            String name = profile.getFirstName() + " " + profile.getLastName();
            String id = profile.getId();
            String profilePicture = profile.getProfilePictureUrl();
            model.addAttribute("profilePicture", profilePicture);
            model.addAttribute("profileName", name);
        }

        return "linkedin/linkedinProfile";
    }

    @RequestMapping(value = {"/connect/linkedin/share"}, method = RequestMethod.POST)
    public String shareLinkOnLinkedIn(@RequestParam("status-msg") String message){

        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
        LinkedIn linkedIn = connection.getApi();

        NewShare.NewShareContent  content = new NewShare.NewShareContent("Spring Social",
                "https://mithsacademy.herokuapp.com",
                "http://s1.postimg.org/v2ogx4957/Teacher_icon.png",
                "Spring Social Integration with Linked In");

        NewShare.NewShareVisibility visibility = new NewShare.NewShareVisibility(NewShare.NewShareVisibilityCode.ANYONE);

        NewShare share = new NewShare(message,content,visibility) ;

        linkedIn.networkUpdateOperations().share(share);



        return "redirect:/connect/linkedin/profile";

    }
}
