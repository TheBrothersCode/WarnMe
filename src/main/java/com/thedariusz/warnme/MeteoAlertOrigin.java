package com.thedariusz.warnme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meteo_alert_origin")
public class MeteoAlertOrigin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_origin_id")
    private Long id;
    private String sourceName;
    private String sourceAuthorId;
    private String originalId;

    public MeteoAlertOrigin(String sourceName, String sourceAuthorId, String originalId) {
        this.sourceName = sourceName;
        this.sourceAuthorId = sourceAuthorId;
        this.originalId = originalId;
    }

    public MeteoAlertOrigin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSourceAuthorId() {
        return sourceAuthorId;
    }

    public void setSourceAuthorId(String sourceAuthorId) {
        this.sourceAuthorId = sourceAuthorId;
    }

    @Override
    public String toString() {
        return "AlertOrigin{" +
                "source='" + sourceName + '\'' +
                ", description='" + sourceAuthorId + '\'' +
                ", originalId='" + originalId + '\'' +
                '}';
    }
}
