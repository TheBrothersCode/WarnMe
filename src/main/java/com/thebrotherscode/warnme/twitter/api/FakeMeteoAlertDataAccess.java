package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakeMeteoAlertDataAccess implements MeteoAlertDao {

    private static final List<MeteoAlert> DB = new ArrayList<>();
    @Override
    public void save(MeteoAlert meteoAlert) {
        DB.add(meteoAlert);
    }

    @Override
    public boolean exists(MeteoAlert meteoAlert) {
        return true;
    }

    @Override
    public List<MeteoAlert> fetchLatest(int number) {
        return DB.stream()
                .sorted(Comparator.comparing(MeteoAlert::getCreationDate).reversed())
                .limit(number)
                .collect(Collectors.toList());

    }


}
