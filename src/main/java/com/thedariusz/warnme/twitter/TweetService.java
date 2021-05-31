package com.thedariusz.warnme.twitter;

import com.thedariusz.warnme.MeteoAlertMapper;
import com.thedariusz.warnme.MeteoAlertService;
import com.thedariusz.warnme.twitter.model.Hashtag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

public class TweetService {

    enum TweetType {
        METEO,
        METEO_ALERT,
        OTHER
    }
    private static final Logger logger = LoggerFactory.getLogger(TweetService.class);
    private static final Map<TweetType, Set<String>> TWEET_TYPE_TO_KEYWORDS = Map.of(
            TweetType.METEO, Set.of("meteo", "weather", "pogoda", "burze", "burza",
                    "upał", "mróz", "meteoimgw", "przymrozki", "temperatura", "hydro",
                    "deszcz", "wichura", "grad", "ulewa",
                    "śnieg", "prognoza"),

            TweetType.METEO_ALERT, Set.of("ostrzegamy", "ostrzeżenia", "ostrzeżenie", "alert",
                    "meteoalert", "uwaga", "alertrcb", "burzaalert")
    );

    private final MeteoAlertService meteoAlertService;
    private final TwitterClient twitterClient;
    private final MeteoAlertMapper meteoAlertMapper;

    public TweetService(MeteoAlertService meteoAlertService, TwitterClient twitterClient, MeteoAlertMapper meteoAlertMapper) {
        this.meteoAlertService = meteoAlertService;
        this.twitterClient = twitterClient;
        this.meteoAlertMapper = meteoAlertMapper;
    }

    public void syncTweets(String twitterUserId) {

        TweetDtoWrapper allTweetsStructure = twitterClient.fetchAllTweets(twitterUserId);
        List<TweetDto> allTweetsBody = allTweetsStructure.getData();
        List<MeteoAlert> meteoAlerts = allTweetsBody.stream()
                .peek(tweetDto -> logger.info("\n Analyzing tweet: ------------------------------\n{}", tweetDto))
                .filter(this::isMeteoAlert)
                .map(meteoAlertMapper::mapToMeteoAlertFromTweet)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    boolean isMeteoAlert(TweetDto tweetDto) {
        TweetType tweetType = getTweetTypeBasedOnHashTags(tweetDto.getEntities().getHashtags());
        return tweetType.equals(TweetType.METEO_ALERT);
    }

    TweetType getTweetTypeBasedOnHashTags(List<Hashtag> hashTags) {
        if (isEmpty(hashTags)) {
            return TweetType.OTHER;
        }

        TweetType tweetType = hashTags
                .stream()
                .map(hashtag -> hashtag.getTag())
                .map(tag -> tag.toLowerCase())
                .anyMatch(getMeteoKeywords()::contains) ? TweetType.METEO : TweetType.OTHER;

        boolean hasMeteoAlertKeywords = hashTags
                .stream()
                .map(hashtag -> hashtag.getTag().toLowerCase())
                .map(tag -> tag.toLowerCase())
                .anyMatch(getMeteoAlertKeywords()::contains);

        if (tweetType.equals(TweetType.METEO) && hasMeteoAlertKeywords) {
            tweetType = TweetType.METEO_ALERT;
        }
        return tweetType;
    }

    private Set<String> getMeteoKeywords() {
        return TWEET_TYPE_TO_KEYWORDS.get(TweetType.METEO);
    }

    private Set<String> getMeteoAlertKeywords() {
        return TWEET_TYPE_TO_KEYWORDS.get(TweetType.METEO_ALERT);
    }

}
