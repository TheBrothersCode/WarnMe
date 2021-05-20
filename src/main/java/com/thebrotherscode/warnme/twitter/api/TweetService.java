package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {
    private final MeteoAlertService meteoAlertService;
    private final TwitterClient twitterClient;
    private final FakeTwitterDataAccess fakeTwitterDataAccess;

    @Autowired
    public TweetService(MeteoAlertService meteoAlertService, TwitterClient twitterClient, FakeTwitterDataAccess fakeTwitterDataAccess) {
        this.meteoAlertService = meteoAlertService;
        this.twitterClient = twitterClient;
        this.fakeTwitterDataAccess = fakeTwitterDataAccess;
    }

    public void syncTweets(String twitterUserId) {
        List<TweetDto> allTweets = twitterClient.fetchAllTweets(twitterUserId);

        //check id fetched Tweets if they've already been handled
        List<TweetDto> filteredTweets = allTweets.stream()
                .filter(tweetDto -> fakeTwitterDataAccess.exists(tweetDto.getTweetId()))
                .collect(Collectors.toList());
        //if not save these id's


        //fetch meteo alerts from tweets


        List<MeteoAlert> meteoAlerts = filteredTweets.stream()
                .filter(this::isMeteoAlert)
                .peek(this::mapToTweet)
                .map(this::mapToMeteoAlert)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    private Tweet mapToTweet(TweetDto tweetDto) {
        Tweet tweet = new Tweet();
        tweet.setTweetId(tweetDto.getTweetId());
        tweet.setText(tweetDto.getText());
        tweet.setAuthor(mapToAuthor(tweetDto.getAuthor()));
        tweet.setCreationDate(tweetDto.getCreationDate());
        tweet.setMediaList(tweetDto.getMediaList());
        tweet.setHashtags(tweetDto.getHashTags());
        return tweet;
    }

    private Author mapToAuthor(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getUsername());
    }

    private boolean isMeteoAlert(TweetDto tweetDto) {
        TweetType tweetType = getTweetType(tweetDto);
        return TweetType.METEO_ALERT.equals(tweetType);
    }

    private MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        String alertCategory = getAlertCategory(tweetDto);
        int alertLevel = getAlertLevel(tweetDto);
        var source = new AlertSource("Twitter", tweetDto.getTweetId());

        return new MeteoAlert(
                alertLevel,
                alertCategory,
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                source,
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
