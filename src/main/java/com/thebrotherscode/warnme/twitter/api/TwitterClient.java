package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Component;

import java.util.List;

//@WebClient
@Component
public class TwitterClient {

    public List<TweetDto> fetchAllTweets() {
        return List.of(
                TweetDto.fakeTweet("1"),
                TweetDto.fakeTweet("2"),
                TweetDto.fakeTweet("3")
        );
    }
}
