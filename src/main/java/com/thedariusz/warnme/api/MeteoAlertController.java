package com.thedariusz.warnme.api;

import com.thedariusz.warnme.twitter.TweetService;
import com.thedariusz.warnme.user.User;
import com.thedariusz.warnme.user.repository.UserDao;
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
    private final UserDao dao;

    @Autowired
    public MeteoAlertController(TweetService tweetService, UserDao dao) {
        this.tweetService = tweetService;
        this.dao = dao;
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
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return "logout";
    }


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

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        dao.saveUser(user);
        return "admin";
    }

}
