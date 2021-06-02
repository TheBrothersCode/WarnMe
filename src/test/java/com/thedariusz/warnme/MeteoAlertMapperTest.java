package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.model.Entity;
import com.thedariusz.warnme.twitter.model.Hashtag;
import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.model.Url;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MeteoAlertMapperTest {

    MeteoAlertCategoryMapper meteoAlertCategoryMapper = new MeteoAlertCategoryMapper();
    MeteoAlertMapper meteoAlertMapper = new MeteoAlertMapper(meteoAlertCategoryMapper);

    @Test
    void shouldMapToMeteoAlertWithLevelNotFound() {
        //given
        final List<String> exampleOfHashtags = List.of("burze", "wichura");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        Entity entity = new Entity(null, hashtags);

        TweetDto tweetWithoutMeaningfulText = TweetDto.builder()
                .withId("1139834822011084801")
                .withText("test")
                .withAuthorId("1139834822011084801")
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withEntity(entity)
                .build();

        //when
        final MeteoAlert meteoAlert = meteoAlertMapper.mapToMeteoAlertFromTweet(tweetWithoutMeaningfulText);

        //then
        final MeteoAlertOrigin meteoAlertOrigin = new MeteoAlertOrigin("Twitter", "1139834822011084801", "1139834822011084801");
        final MeteoAlert expectedAlertWithLeveNotFound = new MeteoAlert(0, Set.of("burze", "wichura"),
                "2021-05-06T10:13:17.000Z", "test", "1139834822011084801", null);

        assertThat(meteoAlert)
                .usingRecursiveComparison()
                .isEqualTo(expectedAlertWithLeveNotFound);
    }


    @Test
    void shouldMapToMeteoAlertWithLevelOne() {
        //given
        final List<String> exampleOfHashtags = List.of("burze", "wichura");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        Entity entity = new Entity(null, hashtags);

        TweetDto tweetDtoWithAllFields = TweetDto.builder()
                .withId("1139834822011084801")
                .withText("1 stopnia")
                .withAuthorId("1139834822011084801")
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withEntity(entity)
                .build();

        //when
        final MeteoAlert meteoAlert = meteoAlertMapper.mapToMeteoAlertFromTweet(tweetDtoWithAllFields);

        //then
        final MeteoAlertOrigin meteoAlertOrigin = new MeteoAlertOrigin("Twitter", "1139834822011084801", "1139834822011084801");
        final MeteoAlert expectedAlertWithLevelOne = new MeteoAlert(1, Set.of("burze", "wichura"),
                "2021-05-06T10:13:17.000Z", "1 stopnia", "1139834822011084801", null);

        assertThat(meteoAlert)
                .usingRecursiveComparison()
                .isEqualTo(expectedAlertWithLevelOne);
    }

}