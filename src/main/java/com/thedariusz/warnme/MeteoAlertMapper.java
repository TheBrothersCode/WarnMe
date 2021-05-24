package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TweetDto;

import java.util.List;

public class MeteoAlertMapper {

    public MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        return new MeteoAlert(
                getAlertLevel(tweetDto),
                getAlertCategory(tweetDto),
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                new MeteoAlertOrigin("Twitter", tweetDto.getAuthor().getName(), tweetDto.getTweetId()),
                tweetDto.getMediaList()
        );
    }

    private List<String> getAlertCategory(TweetDto tweetDto) {
        return List.of("burze");
    }

    private int getAlertLevel(TweetDto tweetDto) {
        return 1;
    }
}
