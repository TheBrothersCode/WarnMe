package com.thedariusz.warnme.twitter.api;

import java.util.List;

public class MeteoAlert {
    private Long id;
    private int level;
    private String category;
    private String creationDate;
    private String description;
    private AlertOrigin alertOrigin;
    private List<String> media;

    public MeteoAlert(int level, String category, String creationDate, String description, AlertOrigin alertOrigin, List<String> media) {
        this.level = level;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
