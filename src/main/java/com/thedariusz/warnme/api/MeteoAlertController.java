package com.thedariusz.warnme.api;

import com.thedariusz.warnme.twitter.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "login";
    }


}
