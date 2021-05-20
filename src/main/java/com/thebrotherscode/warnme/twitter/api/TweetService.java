package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {
    private static final String SOURCE_NAME = "Twitter";
    private final MeteoAlertService meteoAlertService;
    private final TwitterClient twitterClient;

    @Autowired
    public TweetService(MeteoAlertService meteoAlertService, TwitterClient twitterClient) {
        this.meteoAlertService = meteoAlertService;
        this.twitterClient = twitterClient;
    }

    public void syncTweets(String twitterUserId) {
        List<TweetDto> allTweets = twitterClient.fetchAllTweets(twitterUserId);

        List<MeteoAlert> meteoAlerts = allTweets.stream()
                .filter(this::isMeteoAlert)
                .map(this::mapToMeteoAlert)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    private boolean isMeteoAlert(TweetDto tweetDto) {
        var tweetType = getTweetType(tweetDto);
        return TweetType.METEO_ALERT.equals(tweetType); //only MeteoAlert for now
    }

    private MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        return new MeteoAlert(
                getAlertLevel(tweetDto),
                getAlertCategory(tweetDto),
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                new AlertOrigin(SOURCE_NAME, tweetDto.getAuthor().getName(), tweetDto.getTweetId()),
                tweetDto.getMediaList());
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
