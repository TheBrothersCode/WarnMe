package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class TwitterController {
    private final TweetService tweetService;

    @Autowired
    public TwitterController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public void addTwitterAlert(@RequestBody TweetDto tweetDto) {
        tweetService.getTweet(tweetDto);
    }
}
