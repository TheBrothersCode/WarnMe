package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeteoAlertService {
    private final MeteoAlertDao meteoAlertDao;

    @Autowired
    public MeteoAlertService(@Qualifier("fakeDao") MeteoAlertDao meteoAlertDao) {
        this.meteoAlertDao = meteoAlertDao;
    }

    public void save(MeteoAlert meteoAlert) {
        meteoAlertDao.save(meteoAlert);
    }

    public boolean isNew(MeteoAlert meteoAlert) {
        return meteoAlertDao.exists(meteoAlert);
    }

    public void save(List<MeteoAlert> meteoAlerts) {
        List<MeteoAlert> alertsFromDB = meteoAlertDao.fetchLatest(1);
        Optional<String> latestDate = alertsFromDB.stream()
                .findFirst()
                .map(MeteoAlert::getCreationDate);

        if (latestDate.isPresent()) {
            meteoAlerts.stream()
                    .filter(a -> a.getCreationDate().compareTo(latestDate.get())>0)
                    .forEach(meteoAlertDao::save);
        } else {
            meteoAlerts.forEach(meteoAlertDao::save);
        }

        return;
    }
}
