package com.thedariusz.warnme.twitter;

import java.util.List;

public interface TwitterClient {

    List<TweetDto> fetchAllTweets(String twitterUserId);

}
