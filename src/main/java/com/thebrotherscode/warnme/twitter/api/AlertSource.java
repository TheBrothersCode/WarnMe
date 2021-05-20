package com.thebrotherscode.warnme.twitter.api;

public class AlertSource {
    private String name;
    private String alertId;

    public AlertSource(String name, String alertId) {
        this.name = name;
        this.alertId = alertId;
    }

    public String getName() {
        return name;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }
}
