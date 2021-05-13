package com.thebrotherscode.warnme.twitter.api;

public interface MeteoAlertDao {
    void saveMeteoAlert(MeteoAlert meteoAlert);

    boolean exists(MeteoAlert meteoAlert);
}
