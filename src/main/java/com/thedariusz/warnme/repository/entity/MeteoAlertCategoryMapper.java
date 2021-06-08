package com.thedariusz.warnme.repository.entity;

import com.thedariusz.warnme.repository.entity.MeteoAlertCategoryEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MeteoAlertCategoryMapper {

    public Set<String> toModel(Set<MeteoAlertCategoryEntity> entities) {
        return entities.stream()
                .map(entity -> entity.getName())
                .collect(Collectors.toSet());
    }

    public Set<MeteoAlertCategoryEntity> toEntity(Set<String> categories) {
        return categories.stream()
                .map(name -> new MeteoAlertCategoryEntity(name))
                .collect(Collectors.toSet());
    }
}
