package com.thedariusz.warnme.api;

import com.thedariusz.warnme.twitter.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping(value = "/alerts")
public class MeteoAlertController {

    private final TweetService tweetService;

    @Autowired
    public MeteoAlertController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void fetchAllAlerts(@PathVariable("id") String twitterUserId) {
        tweetService.syncTweets(twitterUserId);
    }

    @GetMapping
    public String getMainView() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/alerts";
//        return "login";
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return "logout";
    }

//    @PostMapping("/login")
//    public String getLoginForm(Authentication authentication) {
//        System.out.println("im here");
//        return authentication.getName();
//    }

    @PostMapping("/perform_login")
    @ResponseBody
    public String getAdminPage() {
        return "Im on the admin page";
    }

    @GetMapping("/twitter")
    public String getTwitterView() {
        return "twitter";
    }

    @GetMapping("/error")
    public String getErrorView() {
        return "error";
    }


}
