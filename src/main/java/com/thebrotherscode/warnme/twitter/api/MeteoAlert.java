package com.thebrotherscode.warnme.twitter.api;

public class MeteoAlert {
    private Long id;
    private int level;
    private String category;
    private String creationDate;

    public MeteoAlert(Long id, int level, String category, String creationDate) {
        this.id = id;
        this.level = level;
        this.category = category;
        this.creationDate = creationDate;
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

}
