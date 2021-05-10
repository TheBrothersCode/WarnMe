package com.thebrotherscode.warnme.twitter.api;


import java.util.List;

public class Tweet {
    private Long id;
    private String text;
    private Author author;
    private String creationDate;
    private List<String> mediaList;
    private List<String> hashtags;

    public Tweet(Long id, String text, Author author, String creationDate, List<String> mediaList, List<String> hashtags) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.creationDate = creationDate;
        this.mediaList = mediaList;
        this.hashtags = hashtags;
    }

    public Tweet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<String> mediaList) {
        this.mediaList = mediaList;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
