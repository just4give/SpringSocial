package com.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * Created by mithundas on 11/28/14.
 */

@Controller
public class FacebookController {

    @Autowired
    ConnectionRepository connectionRepository;

//    @Autowired
//    ConnectController connectController;

    @RequestMapping("/connect/facebook/profile")
    public String getFacebookProfile(Model model){

        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        System.out.println(connection);

        Facebook facebook = connection.getApi();
        FacebookProfile profile = facebook.userOperations().getUserProfile();
        String name = profile.getName();
        String id = profile.getId();
        model.addAttribute("profilePicture","http://graph.facebook.com/"+id+"/picture");
        model.addAttribute("profileName",name);
        List<String> friendIds = facebook.friendOperations().getFriendIds();

//        for (int i=0; i<friendProfiles.size();i++){
//            FacebookProfile frndProfile = friendProfiles.get(i);
//            System.out.println(frndProfile.getName());
//        }

        List<Reference> friends = facebook.friendOperations().getFriendLists();
        System.out.println("List of friends:"+friends.size());
        List<Post> feeds = facebook.feedOperations().getFeed();
        //List<Reference> friends = facebook.friendOperations().getFriends();
        //facebook.feedOperations().updateStatus("I'm trying out Spring Social!"); --worked
        //https://graph.facebook.com/me/permissions?access_token=CAAVxZCIYsU08BA

        /* read logged-in user posts */
//        for (int i = 0; i < feeds.size() ; i++) {
//            Post post = feeds.get(i);
//
//            System.out.println("Type:" + post.getType().name());
//            System.out.println("Name:"+ post.getStory());
//        }



        return "facebook/facebookProfile";
    }


    @RequestMapping(value = {"/connect/facebook/share"}, method = RequestMethod.POST)
    public String shareLinkOnFacebook(@RequestParam("status-msg") String message){

        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        Facebook facebook = connection.getApi();
        FacebookProfile profile = facebook.userOperations().getUserProfile();
        FacebookLink link = new FacebookLink("https://mithsacademy.herokuapp.com",
                "Spring Social",
                "Boots up with Spring Social Sign-in",
                "Step by step tutorial on Spring Social integration with Spring Security coming up. This link is posted from " +
                        "dev.");
        facebook.feedOperations().postLink(message, link);

//        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
//        map.set("link", link.getLink());
//        map.set("name", link.getName());
//        map.set("caption", link.getCaption());
//        map.set("description", link.getDescription());
//        map.set("message", message);
//        map.set("picture","http://s1.postimg.org/v2ogx4957/Teacher_icon.png");
//
//        facebook.publish("","",map);

        return "redirect:/";

    }

}
