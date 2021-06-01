package com.thedariusz.warnme.twitter;

import com.thedariusz.warnme.MeteoAlertOrigin;

import java.util.List;
import java.util.Set;

public class MeteoAlert {
    private Long id;
    private int level;
    private Set<String> categories;
    private String creationDate;
    private String description;
    private String externalId;
    private List<String> media;
//    private MeteoAlertOrigin meteoAlertOrigin;

//    public MeteoAlert(int level, Set<String> categories, String creationDate, String description, MeteoAlertOrigin meteoAlertOrigin, List<String> media) {
//        this.level = level;
//        this.categories = categories;
//        this.creationDate = creationDate;
//        this.description = description;
//        this.meteoAlertOrigin = meteoAlertOrigin;
//        this.media = media;
//    }


    public MeteoAlert(Long id, int level, Set<String> categories, String creationDate, String description, String externalId, List<String> media) {
        this.id = id;
        this.level = level;
        this.categories = categories;
        this.creationDate = creationDate;
        this.description = description;
        this.externalId = externalId;
        this.media = media;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public MeteoAlertOrigin getMeteoAlertOrigin() {
//        return meteoAlertOrigin;
//    }
//
//    public void setMeteoAlertOrigin(MeteoAlertOrigin meteoAlertOrigin) {
//        this.meteoAlertOrigin = meteoAlertOrigin;
//    }
//
//    public MeteoAlertOrigin getAlertSource() {
//        return meteoAlertOrigin;
//    }
//
//    public String getAlertOriginalId() {
//        return meteoAlertOrigin.getOriginalId();
//    }
//
//    public void setAlertSource(MeteoAlertOrigin meteoAlertOrigin) {
//        this.meteoAlertOrigin = meteoAlertOrigin;
//    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString() {
        return "MeteoAlert{" +
                ", level=" + level +
                ", categories='" + categories + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                ", externalId=" + externalId +
                ", media=" + media +
                '}';
    }
}
