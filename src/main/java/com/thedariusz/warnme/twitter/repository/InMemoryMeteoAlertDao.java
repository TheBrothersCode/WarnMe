package com.thedariusz.warnme.twitter.repository;

import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.MeteoAlertDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMeteoAlertDao implements MeteoAlertDao {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryMeteoAlertDao.class);
    private static final List<MeteoAlert> DB_METEO_ALERTS = new ArrayList<>();

    @Override
    public void save(MeteoAlert meteoAlert) {
        DB_METEO_ALERTS.add(meteoAlert);
        logger.info(meteoAlert.toString());
    }

    @Override
    public boolean exists(MeteoAlert meteoAlert) {
        return true;
    }

    @Override
    public boolean existsByExternalId(MeteoAlert newAlert) {
        for (MeteoAlert meteoAlert : DB_METEO_ALERTS) {
            String alertOriginalId = newAlert.getAlertOriginalId();
            if (meteoAlert.getAlertOriginalId().equals(alertOriginalId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MeteoAlert> fetchLatest(int number) {
        return DB_METEO_ALERTS.stream()
                .sorted(Comparator.comparing(MeteoAlert::getCreationDate).reversed())
                .limit(number)
                .collect(Collectors.toList());

    }


}
