package com.thedariusz.warnme.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thedariusz.warnme.twitter.model.Entity;
import com.thedariusz.warnme.twitter.model.Hashtag;

import java.util.List;

public class TweetDto {

    private String id;
    private String text;
    private String authorId;
    private String createdAt;
    private Entity entities;

    public TweetDto(String id,
                    String text,
                    String authorId,
                    String createdAt,
                    Entity entities) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.entities = entities;
    }

    public TweetDto() {
    }

    public static TweetDtoBuilder builder() {
        return new TweetDtoBuilder();
    }

    public static final class TweetDtoBuilder {
        private String id;
        private String text;
        private String authorId;
        private String createdAt;
        private Entity entity;

        private TweetDtoBuilder() {
        }

        public TweetDtoBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public TweetDtoBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public TweetDtoBuilder withAuthorId(String authorId) {
            this.authorId = authorId;
            return this;
        }

        public TweetDtoBuilder withCreationDate(String creationDate) {
            this.createdAt = creationDate;
            return this;
        }

        public TweetDtoBuilder withEntity(Entity entity) {
            this.entity = entity;
            return this;
        }

        public TweetDto build() {
            return new TweetDto(id, text, authorId, createdAt, entity);
        }

        public TweetDto fakeTweet(String id, String creationDate, String twitterUserId, Entity entity, String text) {
            return new TweetDto(id, text, twitterUserId, creationDate, entity);
        }
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("author_id")
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Entity getEntities() {
        return entities;
    }

    public List<Hashtag> getHashtagsFromTweet() {
        return entities.getHashtags();
    }

    public void setEntities(Entity entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "TweetDto{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", author_id='" + authorId + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", entities=" + entities +
                '}';
    }
}
