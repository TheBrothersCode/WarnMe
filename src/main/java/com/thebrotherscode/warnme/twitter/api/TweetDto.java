package com.thebrotherscode.warnme.twitter.api;

import java.util.ArrayList;
import java.util.List;

public class TweetDto {
    private final String alertId;
    private final String text;
    private final AuthorDto author;
    private final String creationDate;
    private final TweetType tweetType;
    private final List<String> mediaList;
    private final List<String> hashTags;

    public static TweetDto fakeMeteo() {
        return new TweetDto("1", "test", AuthorDto.fake(), "2021-05-06", TweetType.METEO, List.of("url1", "url2"), List.of("burze"));
    }

    public static TweetDto fakeOther() {
        return new TweetDto("2", "test", AuthorDto.fake(), "2021-05-06", TweetType.OTHER, List.of("photo1", "photo2"), List.of("burze"));
    }

    private TweetDto(String alertId, String text, AuthorDto author, String creationDate, TweetType tweetType, List<String> mediaList, List<String> hashTags) {
        this.alertId = alertId;
        this.text = text;
        this.author = author;
        this.creationDate = creationDate;
        this.tweetType = tweetType;
        this.mediaList = mediaList;
        this.hashTags = hashTags;
    }

    public String getAlertId() {
        return alertId;
    }

    public String getText() {
        return text;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public TweetType getTweetType() {
        return tweetType;
    }

    public List<String> getMediaList() {
        return mediaList;
    }

    public List<String> getHashTags() {
        return hashTags;
    }
}
