package com.thedariusz.warnme.twitter;

import com.thedariusz.warnme.MeteoAlertMapper;
import com.thedariusz.warnme.MeteoAlertService;
import reactor.core.Disposable;

import java.util.List;
import java.util.stream.Collectors;

public class TweetService {

    private final MeteoAlertService meteoAlertService;
    private final TwitterClient twitterClient;
    private final MeteoAlertMapper meteoAlertMapper;

    public TweetService(MeteoAlertService meteoAlertService, TwitterClient twitterClient, MeteoAlertMapper meteoAlertMapper) {
        this.meteoAlertService = meteoAlertService;
        this.twitterClient = twitterClient;
        this.meteoAlertMapper = meteoAlertMapper;
    }

    public void syncTweets(String twitterUserId) {
        List<TweetDto> allTweets = twitterClient.fetchAllTweets(twitterUserId);

        List<MeteoAlert> meteoAlerts = allTweets.stream()
                .filter(meteoAlertMapper::isMeteoAlert)
                .map(meteoAlertMapper::mapToMeteoAlert)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    public TweetDto getSingleTweet(String tweetId) {
//        TweetDto singleTweet = twitterClient.getSingleTweet(tweetId);
        Disposable singleTweet = twitterClient.getSingleTweet(tweetId);
        return null;
    }
}
