package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.api.MeteoAlert;
import com.thedariusz.warnme.twitter.api.MeteoAlertDao;

import java.util.List;

public class MySqlMeteoAlertDao implements MeteoAlertDao {

    @Override
    public void save(MeteoAlert meteoAlert) {

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
    public List<MeteoAlert> fetchLatest(int number) {
        return null;
    }
}
