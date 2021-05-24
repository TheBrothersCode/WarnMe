package com.thedariusz.warnme.twitter.api;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class TweetService {

    private static final Set<String> METEO_KEYWORDS =
            Set.of("meteo", "weather", "imgw", "pogoda", "burze", "burza",
                    "upał", "mróz", "meteoimgw", "przymrozki", "temperatura", "hydro",
                    "deszcz", "wichura", "grad", "ulewa",
                    "śnieg", " prognoza");

    private static final Set<String> METEO_ALERTS_KEYWORDS =
            Set.of("ostrzegamy", "ostrzeżenia", "ostrzeżenie", "alert",
                    "meteoalert", "uwaga", "alertrcb", "burzaalert");

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
                .filter(this::isMeteoAlert)
                .map(meteoAlertMapper::mapToMeteoAlert)
                .collect(Collectors.toList());

        meteoAlertService.save(meteoAlerts);
    }

    private boolean isMeteoAlert(TweetDto tweetDto) {
        var tweetType = getTweetType(tweetDto);
        return TweetType.METEO_ALERT.equals(tweetType);
    }

    private TweetType getTweetType(TweetDto tweetDto) {
        List<String> hashTags = tweetDto.getHashTags();
        TweetType tweetType = hashTags
                .stream()
                .map(tag -> tag.toLowerCase(Locale.ROOT))
                .anyMatch(METEO_KEYWORDS::contains) ? TweetType.METEO:TweetType.OTHER;

        boolean hasMeteoAlertKeywords = hashTags
                .stream()
                .map(tag -> tag.toLowerCase(Locale.ROOT))
                .anyMatch(METEO_ALERTS_KEYWORDS::contains);

        if (tweetType.equals(TweetType.METEO) && hasMeteoAlertKeywords) {
            tweetType = TweetType.METEO_ALERT;
        }

        return tweetType;
    }
}
