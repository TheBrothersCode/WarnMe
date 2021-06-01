package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.twitter.MeteoAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeteoAlertSpringDao extends JpaRepository<MeteoAlert, Long> {
}
