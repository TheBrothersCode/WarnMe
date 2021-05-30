package com.thedariusz.warnme.twitter.model;

public class Meta {

    private String oldestId;
    private String newestId;
    private String resultCount;
    private String nextToken;

    public String getOldestId() {
        return oldestId;
    }

    public void setOldestId(String oldestId) {
        this.oldestId = oldestId;
    }

    public String getNewestId() {
        return newestId;
    }

    public void setNewestId(String newestId) {
        this.newestId = newestId;
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public String getNextToken() {
        return nextToken;
    }

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public Meta(String oldestId, String newestId, String resultCount, String nextToken) {
        this.oldestId = oldestId;
        this.newestId = newestId;
        this.resultCount = resultCount;
        this.nextToken = nextToken;
    }

    public Meta() {
    }
}
