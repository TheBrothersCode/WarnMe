package com.thedariusz.warnme.twitter.client;

import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.TwitterClient;

import java.util.List;

public class SpringTwitterClient implements TwitterClient {

    public List<TweetDto> fetchAllTweets(String twitterUserId) {
        return List.of();
    }
}
