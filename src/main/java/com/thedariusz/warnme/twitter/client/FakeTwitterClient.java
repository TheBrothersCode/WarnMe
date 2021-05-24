package com.thedariusz.warnme.twitter.client;

import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.TwitterClient;

import java.util.ArrayList;
import java.util.List;

public class FakeTwitterClient implements TwitterClient {

    public List<TweetDto> fetchAllTweets(String twitterUserId) {
        List<TweetDto> tweetDtos = new ArrayList<>();
        if ("1139834822011084801".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.fakeTweet("1", "2021-05-06T10:13:17.000Z", twitterUserId, List.of("burze", "wichura", "ostrzeżenie")),
                    TweetDto.fakeTweet("2", "2021-04-06T11:00:01.000Z", twitterUserId, List.of("pogoda", "góry")),
                    TweetDto.fakeTweet("3", "2021-05-10T22:54:44.000Z", twitterUserId, List.of("wiosna"))
            );
        }
        if ("2979632800".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.fakeTweet("12", "2021-05-11T11:10:10.000Z", twitterUserId, List.of("burze", "alertrcb")),
                    TweetDto.fakeTweet("2", "2021-03-12T17:05:02.000Z", twitterUserId, List.of("upał", "alert")),
                    TweetDto.fakeTweet("3", "2021-02-09T08:55:40.000Z", twitterUserId, List.of("strajk"))
            );
        }

        return tweetDtos;
    }
}
