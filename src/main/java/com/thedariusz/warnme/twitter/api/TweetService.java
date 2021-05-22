package com.thedariusz.warnme.twitter.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TweetService {
    private static final String SOURCE_NAME = "Twitter";
    private static final Logger logger = LoggerFactory.getLogger(TweetService.class);
    private static final Set<String> METEO_KEYWORDS =
            Set.of("meteo", "weather", "imgw", "pogoda", "burze", "burza",
                    "upał", "mróz", "meteoimgw", "przymrozki", "temperatura", "hydro",
                    "deszcz", "wichura", "grad", "ulewa",
                    "śnieg", " prognoza");

    private static final Set<String> METEO_ALERTS_CATEGORIES =
            Set.of("burze", "burza", "upał", "mróz", "przymrozki", "hydro", "deszcz", "wichura", "grad", "ulewa", "śnieg",
                    "burze z gradem", "intensywne opady deszczu", "intensywne opady śniegu", "mgła intensywnie osadzająca szadź",
                    "oblodzenie", "opady marznące", "opady śniegu", "roztopy", "silny deszcze z burzami",
                    "gęsta mgła", "silny mróz", "silny wiatr", "zawieje", "zamiecie śnieżne");

    private static final Set<String> ALERTS_KEYWORDS =
            Set.of("ostrzegamy", "ostrzeżenia", "ostrzeżenie", "alert",
                    "meteoalert", "uwaga", "alertrcb", "burzaalert");

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
        return TweetType.METEO_ALERT.equals(tweetType);
    }

    private MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        return new MeteoAlert(
                getAlertLevel(tweetDto),
                getAlertCategories(tweetDto),
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                new AlertOrigin(SOURCE_NAME, tweetDto.getAuthor().getName(), tweetDto.getTweetId()),
                tweetDto.getMediaList());
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
                .anyMatch(ALERTS_KEYWORDS::contains);

        if (tweetType.equals(TweetType.METEO) && hasMeteoAlertKeywords) {
            tweetType = TweetType.METEO_ALERT;
        }

        return tweetType;
    }

    private Set<String> getAlertCategories(TweetDto tweetDto) {
        List<String> hashTags = tweetDto.getHashTags();
        String tweetText = tweetDto.getText().toLowerCase(Locale.ROOT);
        List<String> categoriesFromHashtags = hashTags
                .stream()
                .filter(METEO_ALERTS_CATEGORIES::contains)
                .collect(Collectors.toList());

        List<String> categoriesFromTweetText = METEO_ALERTS_CATEGORIES
                .stream()
                .filter(tweetText::contains)
                .collect(Collectors.toList());

        return Stream.of(categoriesFromTweetText, categoriesFromHashtags)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private int getAlertLevel(TweetDto tweetDto) {
        var alertLevel = 0;
        var textAlertLevelPattern = "(\\d)\\s*(?=stopni|°)";
        var pattern = Pattern.compile(textAlertLevelPattern);
        var matcher = pattern.matcher(tweetDto.getText());

        try {
            if (matcher.find()) {
                alertLevel = Integer.parseInt(matcher.group(1));
            }
        } catch (NumberFormatException e) {
            logger.error("Found text alert level pattern but couldn't be convert to integer!", e);
        }
        return alertLevel ;
    }
}
