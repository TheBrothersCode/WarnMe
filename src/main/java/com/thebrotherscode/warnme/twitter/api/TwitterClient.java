package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Component;

import java.util.List;

//@WebClient
@Component
public class TwitterClient {

    public List<TweetDto> fetchAllTweets() {
        return List.of(
                new TweetDto("1", "testowy tweet z dupy1", null, "2021-05-13 20:00", List.of(), List.of()),
                new TweetDto("2", "testowy tweet z dupy2", null, "2021-05-13 20:00", List.of(), List.of()),
                new TweetDto("3", "testowy tweet z dupy3", null, "2021-05-13 20:00", List.of(), List.of())
        );
    }
}
