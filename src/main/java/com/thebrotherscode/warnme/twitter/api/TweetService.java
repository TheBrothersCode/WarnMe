package com.thebrotherscode.warnme.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
    private final MeteoAlertService meteoAlertService;

    @Autowired
    public TweetService(MeteoAlertService meteoAlertService) {
        this.meteoAlertService = meteoAlertService;
    }

    public void getTweet(TweetDto tweetDto) {
        Tweet tweet = mapTweetDtoToTweet(tweetDto);
        TweetType tweetType = checkTweetType(tweet);
        if (TweetType.METEO.equals(tweetType)) {
            meteoAlertService.getMeteoAlertFromTweet(tweet);
        }

    }

    private TweetType checkTweetType(Tweet tweet) {
        return TweetType.METEO;
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


}
