package com.thedariusz.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alerts")
public class AlertController {

    private final TweetService tweetService;

    @Autowired
    public AlertController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    @RequestMapping("/{id}")
    public void fetchAllAlerts(@PathVariable("id") String twitterUserId) {
//        String twitterUserId="1";
        tweetService.syncTweets(twitterUserId);
        //burzeService.fetchStormsAlerts()
    }



}
