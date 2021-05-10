package com.thebrotherscode.warnme.twitter.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class TweetDto {
    private final String tweetId;
    private final String text;
    private final AuthorDto author;
    private final String creationDate;
    private final List<String> mediaList;
    private final List<String> hashTags;

//    public static TweetDto fakeMeteo() {
//        return new TweetDto("1", "test", AuthorDto.fake(), "2021-05-06",  List.of("url1", "url2"), List.of("burze"));
//    }
//
//    public static TweetDto fakeOther() {
//        return new TweetDto("2", "test", AuthorDto.fake(), "2021-05-06",  List.of("photo1", "photo2"), List.of("burze"));
//    }

    public TweetDto(@JsonProperty("tweetId") String tweetId,
                    @JsonProperty("text") String text,
                    @JsonProperty("author") AuthorDto author,
                    @JsonProperty("creationDate") String creationDate,
                    @JsonProperty("mediaList") List<String> mediaList,
                    @JsonProperty("hashTags") List<String> hashTags) {
        this.tweetId = tweetId;
        this.text = text;
        this.author = author;
        this.creationDate = creationDate;
        this.mediaList = List.copyOf(mediaList);
        this.hashTags = List.copyOf(hashTags);
    }


    public String getTweetId() {
        return tweetId;
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


    public List<String> getMediaList() {
        return mediaList;
    }

    public List<String> getHashTags() {
        return hashTags;
    }
}
