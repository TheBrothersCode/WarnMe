package com.thedariusz.warnme.twitter.api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakeMeteoAlertDataAccess implements MeteoAlertDao {

    private static final List<MeteoAlert> DB_METEO_ALERTS = new ArrayList<>();

    @Override
    public void save(MeteoAlert meteoAlert) {
        DB_METEO_ALERTS.add(meteoAlert);
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
