package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Component;

import java.util.List;

//@WebClient
@Component
public class TwitterClient {

    public List<TweetDto> fetchAllTweets() {
        return List.of(
                TweetDto.fakeTweet("1", "2021-05-06T10:13:17.000Z"),
                TweetDto.fakeTweet("2", "2021-04-06T11:00:01.000Z"),
                TweetDto.fakeTweet("3", "2021-05-010T22:54:44.000Z")
        );
    }
}
