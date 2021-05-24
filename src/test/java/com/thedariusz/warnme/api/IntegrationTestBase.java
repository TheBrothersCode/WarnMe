package com.thedariusz.warnme.api;

import java.util.List;

class IntegrationTestBase {

    protected static final String ALERTS_PATH = "/alerts/2979632800";

    protected static class TweetDtoTest {
        public String tweetId;
        public String text;
        public AuthorDtoTest author;
        public String creationDate;
        public List<String> mediaList;
        public List<String> hashTags;

        public TweetDtoTest(String tweetId, String text, AuthorDtoTest author, String creationDate, List<String> mediaList, List<String> hashTags) {
            this.tweetId = tweetId;
            this.text = text;
            this.author = author;
            this.creationDate = creationDate;
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
                    "alertId='" + tweetId + '\'' +
                    ", text='" + text + '\'' +
                    ", author=" + author +
                    ", creationDate='" + creationDate + '\'' +
                    ", mediaList=" + mediaList +
                    ", hashTags=" + hashTags +
                    '}';
        }
    }

}
