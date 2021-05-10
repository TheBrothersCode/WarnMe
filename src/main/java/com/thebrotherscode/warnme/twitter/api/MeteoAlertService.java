package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MeteoAlertService {
    private final MeteoAlertDao meteoAlertDao;

    @Autowired
    public MeteoAlertService(@Qualifier("fakeDao") MeteoAlertDao meteoAlertDao) {
        this.meteoAlertDao = meteoAlertDao;
    }

    public void getMeteoAlertFromTweet(Tweet tweet) {
        int level = checkLevelOfAlertFromTweet(tweet);
        String category = getCategoryFromHashtags(tweet);
        meteoAlertDao.saveMeteoAlert(new MeteoAlert(1l, level, category));
    }

    private String getCategoryFromHashtags(Tweet tweet) {
        return "burze";
    }

    private int checkLevelOfAlertFromTweet(Tweet tweet) {
        return 1;
    }
}
