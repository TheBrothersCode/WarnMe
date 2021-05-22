package com.thedariusz.warnme.twitter.api;

public class AlertOrigin {
    private String sourceName;
    private String sourceDesc;
    private String originalId;

    public AlertOrigin(String sourceName, String sourceDesc, String originalId) {
        this.sourceName = sourceName;
        this.sourceDesc = sourceDesc;
        this.originalId = originalId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }
}
