package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    private final TweetService tweetService;

    @Autowired
    public AlertController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public void fetchAllAlerts() {
        tweetService.syncTweets();
        //burzeService.fetchStormsAlerts()
    }

    @GetMapping
    public List<MeteoAlertDto> showAllAlerts() {
        return List.of();
    }

    @PostMapping("/admin/init")
    public void initializeAlerts(List<MeteoAlertDto> alerts) {
        tweetService.saveTweets(alerts);
    }

}
