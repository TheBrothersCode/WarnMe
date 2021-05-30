package com.thedariusz.warnme.twitter.model;

public class Media {
    private String type;
    private int height;
    private int width;
    private String mediaKey;
    private String url;

    public Media() {
    }

    public Media(String type, int height, int width, String mediaKey, String url) {
        this.type = type;
        this.height = height;
        this.width = width;
        this.mediaKey = mediaKey;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getMediaKey() {
        return mediaKey;
    }

    public void setMediaKey(String mediaKey) {
        this.mediaKey = mediaKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
