package com.thedariusz.warnme.twitter.client;

import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.TwitterClient;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FakeTwitterClient implements TwitterClient {

    public List<TweetDto> fetchAllTweets(String twitterUserId) {
        List<TweetDto> tweetDtos = new ArrayList<>();
        if ("1139834822011084801".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.builder().fakeTweet("1",  OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), twitterUserId, List.of("burze", "wichura", "ostrzeżenie")),
                    TweetDto.builder().fakeTweet("2",  OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), twitterUserId, List.of("pogoda", "góry")),
                    TweetDto.builder().fakeTweet("3",  OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), twitterUserId, List.of("wiosna"))
            );
        }
        if ("2979632800".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.builder().fakeTweet("10", "2021-05-11T11:10:10.000Z", twitterUserId, List.of("burze", "alertrcb")),
                    TweetDto.builder().fakeTweet("20", "2021-03-12T17:05:02.000Z", twitterUserId, List.of("upał", "alert")),
                    TweetDto.builder().fakeTweet("30", "2021-02-09T08:55:40.000Z", twitterUserId, List.of("strajk"))
            );
        }

        return tweetDtos;
    }
}
