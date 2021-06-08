package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.MeteoAlertCategory;
import com.thedariusz.warnme.MeteoAlertCategoryEntity;
import com.thedariusz.warnme.twitter.MeteoAlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeteoAlertCategorySpringDao extends JpaRepository<MeteoAlertCategoryEntity, Long> {



}
