package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeDao")
public class FakeMeteoAlertDataAccessService implements MeteoAlertDao {

    private static final List<MeteoAlert> DB = new ArrayList<>();
    @Override
    public void saveMeteoAlert(MeteoAlert meteoAlert) {
        DB.add(meteoAlert);
    }

    @Override
    public boolean exists(MeteoAlert meteoAlert) {
        return true;
    }
}
