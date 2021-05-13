package com.thebrotherscode.warnme.twitter.api;

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

    public void save(MeteoAlert meteoAlert) {
        meteoAlertDao.saveMeteoAlert(meteoAlert);
    }

    public boolean isNew(MeteoAlert meteoAlert) {
        return meteoAlertDao.exists(meteoAlert);
    }

    public void save(List<MeteoAlert> meteoAlerts) {
        //logika tylko nowe
        //save z dao
    }
}
