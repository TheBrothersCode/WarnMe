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

    public void syncTweets() {
        List<TweetDto> allTweets = twitterClient.fetchAllTweets();
//        allTweets.stream()
//                .filter(this::isMeteoAlert)
//                .map(this::mapToMeteoAlert)
//                .filter(meteoAlertService::isNew)
//                .forEach(meteoAlertService::save);

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

        return null;
    }

    private TweetType getTweetType(TweetDto tweet) {
        return TweetType.METEO_ALERT;
    }

    private Tweet mapTweetDtoToTweet(TweetDto tweetDto) {
        Tweet tweet = new Tweet();
        tweet.setId(Long.parseLong(tweetDto.getTweetId()));
        tweet.setText(tweetDto.getText());
        tweet.setAuthor(mapAuthorDtoToAuthor(tweetDto.getAuthor()));
        tweet.setCreationDate(tweetDto.getCreationDate());
        tweet.setMediaList(tweetDto.getMediaList());
        tweet.setHashtags(tweetDto.getHashTags());
        return tweet;
    }

    private Author mapAuthorDtoToAuthor(AuthorDto authorDto) {
        return new Author(Long.parseLong(authorDto.getId()), authorDto.getName(), authorDto.getUsername());
    }


    public void saveTweets(List<MeteoAlertDto> alerts) {
        return;
    }

    private String getCategoryFromHashtags(Tweet tweet) {
        return "burze";
    }

    private int checkLevelOfAlertFromTweet(Tweet tweet) {
        return 1;
    }
}
