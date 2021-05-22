package com.thedariusz.warnme.twitter.api;

import java.util.List;

public interface MeteoAlertDao {
    void save(MeteoAlert meteoAlert);

    boolean exists(MeteoAlert meteoAlert);

    boolean existsByExternalId(MeteoAlert meteoAlert);

    List<MeteoAlert> fetchLatest(int number);
}
