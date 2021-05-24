package com.thedariusz.warnme;

import com.thedariusz.warnme.twitter.AuthorDto;
import com.thedariusz.warnme.twitter.MeteoAlert;
import com.thedariusz.warnme.twitter.TweetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MeteoAlertMapperTest {

    MeteoAlertCategoryMapper meteoAlertCategoryMapper = new MeteoAlertCategoryMapper();
    MeteoAlertMapper meteoAlertMapper = new MeteoAlertMapper(meteoAlertCategoryMapper);

    @Test
    void shouldMapToMeteoAlertWithLevelNotFound() {
        //given
        TweetDto tweetWithoutMeaningfulText = TweetDto.builder()
                .withTweetId("1")
                .withText("test")
                .withAuthor(AuthorDto.fake("1139834822011084801"))
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withMediaList(List.of("url1", "url2"))
                .withHashTags(List.of("burze", "wichura", "ostrzeżenie"))
                .build();

        //when
        final MeteoAlert meteoAlert = meteoAlertMapper.mapToMeteoAlert(tweetWithoutMeaningfulText);

        //then
        final MeteoAlertOrigin meteoAlertOrigin = new MeteoAlertOrigin("Twitter", "imgw", "1");
        final MeteoAlert expectedAlertWithLeveNotFound = new MeteoAlert(0, Set.of("burze", "wichura"), "2021-05-06T10:13:17.000Z", "test", meteoAlertOrigin, List.of("url1", "url2"));

        assertThat(meteoAlert)
                .usingRecursiveComparison()
                .isEqualTo(expectedAlertWithLeveNotFound);
    }


    @Test
    void shouldMapToMeteoAlertWithLevelOne() {
        //given
        TweetDto tweetDtoWithAllFields = TweetDto.builder()
                .withTweetId("1")
                .withText("1 stopnia")
                .withAuthor(AuthorDto.fake("1139834822011084801"))
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withMediaList(List.of("url1", "url2"))
                .withHashTags(List.of("burze", "wichura", "ostrzeżenie"))
                .build();

        //when
        final MeteoAlert meteoAlert = meteoAlertMapper.mapToMeteoAlert(tweetDtoWithAllFields);

        //then
        final MeteoAlertOrigin meteoAlertOrigin = new MeteoAlertOrigin("Twitter", "imgw", "1");
        final MeteoAlert expectedAlertWithLevelOne = new MeteoAlert(1, Set.of("burze", "wichura"), "2021-05-06T10:13:17.000Z", "1 stopnia", meteoAlertOrigin, List.of("url1", "url2"));

        assertThat(meteoAlert)
                .usingRecursiveComparison()
                .isEqualTo(expectedAlertWithLevelOne);
    }

}