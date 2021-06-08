package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.MeteoAlertEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Entity(name = "meteo_alert_category")
public class MeteoAlertCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany( mappedBy = "categories")
    private Set<MeteoAlertEntity> meteoAlertEntity;

    public MeteoAlertCategoryEntity() {
    }

    public MeteoAlertCategoryEntity( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MeteoAlertEntity> getMeteoAlertEntity() {
        return meteoAlertEntity;
    }

    public void setMeteoAlertEntity(Set<MeteoAlertEntity> meteoAlertEntity) {
        this.meteoAlertEntity = meteoAlertEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;

        MeteoAlertCategoryEntity entity = (MeteoAlertCategoryEntity) o;

        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
