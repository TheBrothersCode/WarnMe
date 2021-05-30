package com.thedariusz.warnme.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thedariusz.warnme.twitter.model.Entity;

public class TweetDto {
    private String id;
    private String text;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("created_at")
    private String createdAt;
    private Entity entities;

    public TweetDto(String id,
                    String text,
                    String authorId,
                    String createdAt,
                    Entity entity) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.entities = entity;
    }

    public TweetDto() {
    }
    public static TweetDtoBuilder builder() {
        return new TweetDtoBuilder();
    }

    public static final class TweetDtoBuilder {
        private String id;
        private String text;
        private String author_id;
        private String created_at;
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
            this.author_id = authorId;
            return this;
        }

        public TweetDtoBuilder withCreationDate(String creationDate) {
            this.created_at = creationDate;
            return this;
        }

        public TweetDtoBuilder withEntity(Entity entity) {
            this.entity = entity;
            return this;
        }

        public TweetDto build() {
            return new TweetDto(id, text, author_id, created_at, entity);
        }

        public TweetDto fakeTweet(String id, String creationDate, String twitterUserId, Entity entity, String text) {
            return new TweetDto(id, text, twitterUserId, creationDate,  entity);
        }
    }
    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreationDate() {
        return createdAt;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Entity getEntities() {
        return entities;
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
