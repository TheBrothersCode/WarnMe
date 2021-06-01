package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.MeteoAlertDao;
import com.thedariusz.warnme.twitter.MeteoAlert;

import java.util.List;

public class PostgresMeteoAlertDao implements MeteoAlertDao {

    private final MeteoAlertSpringDao dao;

    public PostgresMeteoAlertDao(MeteoAlertSpringDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(MeteoAlert meteoAlert) {
        dao.save(meteoAlert);
    }

    @Override
    public boolean exists(MeteoAlert meteoAlert) {
        return false;
    }

    @Override
    public boolean existsByExternalId(MeteoAlert meteoAlert) {
        return false;
    }

    @Override
    public List<MeteoAlert> fetchAll() {
        return List.of();
    }

    @Override
    public void deleteAll() {
        //skip this
    }

}
