package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {
    private final MeteoAlertService meteoAlertService;
    private final TwitterClient twitterClient;

    @Autowired
    public TweetService(MeteoAlertService meteoAlertService, TwitterClient twitterClient) {
        this.meteoAlertService = meteoAlertService;
        this.twitterClient = twitterClient;
    }

    public void syncTweets(String twitterUserId) {
        List<TweetDto> allTweets = twitterClient.fetchAllTweets(twitterUserId);

        //check id fetched Tweets if they've already been handled

        //if not save these id's

        //fetch meteo alerts from tweets


        List<MeteoAlert> meteoAlerts = allTweets.stream()
                .filter(this::isMeteoAlert)
                .map(this::mapToMeteoAlert)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    private boolean isMeteoAlert(TweetDto tweetDto) {
        TweetType tweetType = getTweetType(tweetDto);
        return TweetType.METEO_ALERT.equals(tweetType);
    }

    private MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        String alertCategory = getAlertCategory(tweetDto);
        int alertLevel = getAlertLevel(tweetDto);
        var source = "Twitter";

        return new MeteoAlert(
                alertLevel,
                alertCategory,
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                source);
    }

    private TweetType getTweetType(TweetDto tweetDto) {
        return TweetType.METEO_ALERT;
    }

    private String getAlertCategory(TweetDto tweetDto) {
        return "burze";
    }

    private int getAlertLevel(TweetDto tweetDto) {
        return 1;
    }
}
