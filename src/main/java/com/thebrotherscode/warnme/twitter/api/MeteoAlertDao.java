package com.thebrotherscode.warnme.twitter.api;

import java.util.List;

public interface MeteoAlertDao {
    void save(MeteoAlert meteoAlert);

    boolean exists(MeteoAlert meteoAlert);

    List<MeteoAlert> fetchLatest(int number);
}
