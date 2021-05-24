package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TweetDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MeteoAlertMapper {

    private enum TweetType {
        METEO,
        METEO_ALERT,
        OTHER
    }

    private static final String DIGIT_BEFORE_TEXT_OR_DEGREE_CHARACTER = "(\\d)\\s*(?=stopni|°)";

    private static final Map<TweetType, Set<String>> TWEET_TYPE_TO_KEYWORDS = Map.of(
            TweetType.METEO, Set.of("meteo", "weather", "imgw", "pogoda", "burze", "burza",
                    "upał", "mróz", "meteoimgw", "przymrozki", "temperatura", "hydro",
                    "deszcz", "wichura", "grad", "ulewa",
                    "śnieg", "prognoza"),

            TweetType.METEO_ALERT, Set.of("ostrzegamy", "ostrzeżenia", "ostrzeżenie", "alert",
                    "meteoalert", "uwaga", "alertrcb", "burzaalert")
    );

    private static final int LEVEL_NOT_FOUND = 0;

    private final MeteoAlertCategoryMapper meteoAlertCategoryMapper;

    public MeteoAlertMapper(MeteoAlertCategoryMapper meteoAlertCategoryMapper) {
        this.meteoAlertCategoryMapper = meteoAlertCategoryMapper;
    }

    public MeteoAlert mapToMeteoAlert(TweetDto tweetDto) {
        return new MeteoAlert(
                getAlertLevel(tweetDto),
                getAlertCategories(tweetDto),
                tweetDto.getCreationDate(),
                tweetDto.getText(),
                new MeteoAlertOrigin("Twitter", tweetDto.getAuthor().getName(), tweetDto.getTweetId()),
                tweetDto.getMediaList()
        );
    }

    private int getAlertLevel(TweetDto tweetDto) {
        var pattern = Pattern.compile(DIGIT_BEFORE_TEXT_OR_DEGREE_CHARACTER);
        var matcher = pattern.matcher(tweetDto.getText());
        try {
            return matcher.find() ? Integer.parseInt(matcher.group(1)) : LEVEL_NOT_FOUND;
        } catch (NumberFormatException e) {
            return LEVEL_NOT_FOUND;
        }
    }

    public boolean isMeteoAlert(TweetDto tweetDto) {
        TweetType tweetType = getTweetType(tweetDto);
        return TweetType.METEO_ALERT.equals(tweetType);
    }

    private Set<String> getAlertCategories(TweetDto tweetDto) {
        final Set<String> categoriesFromHashTags = meteoAlertCategoryMapper.getCategories(tweetDto.getHashTags());
        final Set<String> categoriesFromText = meteoAlertCategoryMapper.getCategoriesFromText(tweetDto.getText());

        return Stream.of(categoriesFromHashTags, categoriesFromText)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private TweetType getTweetType(TweetDto tweetDto) {
        List<String> hashTags = tweetDto.getHashTags();

        TweetType tweetType = hashTags
                .stream()
                .map(String::toLowerCase)
                .anyMatch(getMeteoKeywords()::contains) ? TweetType.METEO : TweetType.OTHER;

        boolean hasMeteoAlertKeywords = hashTags
                .stream()
                .map(String::toLowerCase)
                .anyMatch(getAlertKeywords()::contains);

        if (tweetType.equals(TweetType.METEO) && hasMeteoAlertKeywords) {
            tweetType = TweetType.METEO_ALERT;
        }
        return tweetType;
    }

    private Set<String> getAlertKeywords() {
        return TWEET_TYPE_TO_KEYWORDS.get(TweetType.METEO_ALERT);
    }

    private Set<String> getMeteoKeywords() {
        return TWEET_TYPE_TO_KEYWORDS.get(TweetType.METEO);
    }
}
