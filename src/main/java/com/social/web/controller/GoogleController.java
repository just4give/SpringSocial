package com.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.moments.AddActivity;
import org.springframework.social.google.api.plus.moments.CreateActivity;
import org.springframework.social.google.api.plus.moments.Moment;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.NewShare;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mithundas on 12/2/14.
 */

@Controller
public class GoogleController {

    @Autowired
    ConnectionRepository connectionRepository;

    @RequestMapping("/connect/google/profile")
    public String getGoogleProfile(Model model){

        Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
        System.out.println(connection);
        if(connection!=null) {
            //return "redirect:/auth/linkedin";

            Google google = connection.getApi();
            Person profile = google.plusOperations().getGoogleProfile();


            String name = profile.getDisplayName();
            String id = profile.getId();
            String profilePicture = profile.getImageUrl();
            model.addAttribute("profilePicture", profilePicture);
            model.addAttribute("profileName", name);
        }

        return "google/googleProfile";
    }

    @RequestMapping(value = {"/connect/google/share"}, method = RequestMethod.POST)
    public String shareLinkOnGoogle(@RequestParam("status-msg") String message){

        Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
        Google google = connection.getApi();

        Moment moment = new AddActivity("https://mithsacademy.herokuapp.com");

        google.plusOperations().insertMoment(moment);
        return "redirect:/connect/google/profile";

    }
}
