package com.thebrotherscode.warnme.twitter.api;

import java.util.List;

class IntegrationTestBase {

    protected static final String ALERTS_PATH = "/alerts";

    protected static class TweetDtoTest {
        public String alertId;
        public String text;
        public AuthorDtoTest author;
        public String creationDate;
        public String tweetType;
        public List<String> mediaList;
        public List<String> hashTags;

        public TweetDtoTest(String alertId, String text, AuthorDtoTest author, String creationDate, String tweetType, List<String> mediaList, List<String> hashTags) {
            this.alertId = alertId;
            this.text = text;
            this.author = author;
            this.creationDate = creationDate;
            this.tweetType = tweetType;
            this.mediaList = mediaList;
            this.hashTags = hashTags;
        }

        public TweetDtoTest() {}

        public static class AuthorDtoTest {
            public String id;
            public String name;
            public String username;

            public AuthorDtoTest(String id, String name, String username) {
                this.id = id;
                this.name = name;
                this.username = username;
            }

            public AuthorDtoTest() {
            }

            @Override
            public String toString() {
                return "AuthorDtoTest{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", username='" + username + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "TweetDtoTest{" +
                    "alertId='" + alertId + '\'' +
                    ", text='" + text + '\'' +
                    ", author=" + author +
                    ", creationDate='" + creationDate + '\'' +
                    ", tweetType='" + tweetType + '\'' +
                    ", mediaList=" + mediaList +
                    ", hashTags=" + hashTags +
                    '}';
        }
    }

}
