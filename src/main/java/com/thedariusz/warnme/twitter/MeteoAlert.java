package com.thedariusz.warnme.twitter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="meteo_alert")
public class MeteoAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int level;
    @ElementCollection
    private Set<String> categories;
    private String creationDate;
    private String description;
    private String externalId;
    @ElementCollection
    private List<String> media;

    public MeteoAlert() {
    }

    public MeteoAlert(int level, Set<String> categories, String creationDate, String description, String externalId, List<String> media) {
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
                "id=" + id +
                ", level=" + level +
                ", categories=" + categories +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                ", externalId='" + externalId + '\'' +
                ", media=" + media +
                '}';
    }

}
