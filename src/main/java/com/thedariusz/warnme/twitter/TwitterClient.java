package com.thedariusz.warnme.twitter;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TwitterClient {

    List<TweetDto> fetchAllTweets(String twitterUserId);
    Disposable getSingleTweet(String tweetId);


}
