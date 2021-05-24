package com.thedariusz.warnme.twitter.api;

import java.util.List;

public class MeteoAlertMapper {

    public MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        return new MeteoAlert(
                getAlertLevel(tweetDto),
                getAlertCategory(tweetDto),
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                new AlertOrigin("Twitter", tweetDto.getAuthor().getName(), tweetDto.getTweetId()),
                tweetDto.getMediaList());
    }

    private List<String> getAlertCategory(TweetDto tweetDto) {
        return List.of("burze");
    }

    private int getAlertLevel(TweetDto tweetDto) {
        return 1;
    }
}
