package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.twitter.MeteoAlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeteoAlertSpringDao extends JpaRepository<MeteoAlertEntity, Long> {

    List<MeteoAlertEntity> findByExternalIdIn(List<String> externalIds);
    List<MeteoAlertEntity> findByOrderByCreationDateDesc();
    Optional<MeteoAlertEntity> findFirstByOrderByCreatedAtDesc();

}
