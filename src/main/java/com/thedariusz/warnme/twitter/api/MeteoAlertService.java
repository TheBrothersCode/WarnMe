package com.thedariusz.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeteoAlertService {
    private final MeteoAlertDao meteoAlertDao;

    @Autowired
    public MeteoAlertService(@Qualifier("fakeDao") MeteoAlertDao meteoAlertDao) {
        this.meteoAlertDao = meteoAlertDao;
    }

    public void save(List<MeteoAlert> meteoAlerts) {
        meteoAlerts.stream()
                .filter(meteoAlert -> !meteoAlertDao.existsByExternalId(meteoAlert))
                .forEach(meteoAlert -> meteoAlertDao.save(meteoAlert));
    }
}
