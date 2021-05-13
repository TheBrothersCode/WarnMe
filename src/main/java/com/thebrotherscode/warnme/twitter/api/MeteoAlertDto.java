package com.thebrotherscode.warnme.twitter.api;

public class MeteoAlertDto {
    private String id;
    private String level;
    private String category;
    private String source;

    public MeteoAlertDto(String id, String level, String category, String source) {
        this.id = id;
        this.level = level;
        this.category = category;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
