package com.thedariusz.warnme.twitter.api;

import java.util.List;
import java.util.Set;

public class MeteoAlert {
    private Long id;
    private int level;
    private Set<String> categories;
    private String creationDate;
    private String description;
    private AlertOrigin alertOrigin;
    private List<String> media;

    public MeteoAlert(int level, Set<String> categories, String creationDate, String description, AlertOrigin alertOrigin, List<String> media) {
        this.level = level;
        this.categories = categories;
        this.creationDate = creationDate;
        this.description = description;
        this.alertOrigin = alertOrigin;
        this.media = media;
    }

    public AlertOrigin getAlertSource() {
        return alertOrigin;
    }

    public String getAlertOriginalId() {
        return alertOrigin.getOriginalId();
    }

    public void setAlertSource(AlertOrigin alertOrigin) {
        this.alertOrigin = alertOrigin;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MeteoAlert{" +
                "id=" + id +
                ", level=" + level +
                ", categories='" + categories + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                ", alertOrigin=" + alertOrigin +
                ", media=" + media +
                '}';
    }
}
