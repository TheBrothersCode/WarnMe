package com.thedariusz.warnme.twitter;

import com.thedariusz.warnme.MeteoAlertCategoryMapper;
import com.thedariusz.warnme.MeteoAlertDao;
import com.thedariusz.warnme.MeteoAlertMapper;
import com.thedariusz.warnme.MeteoAlertService;
import com.thedariusz.warnme.twitter.client.FakeTwitterClient;
import com.thedariusz.warnme.twitter.model.Entity;
import com.thedariusz.warnme.twitter.model.Hashtag;
import com.thedariusz.warnme.twitter.repository.InMemoryMeteoAlertDao;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TweetServiceTest {
    MeteoAlertDao meteoAlertDao = new InMemoryMeteoAlertDao();
    MeteoAlertService meteoAlertService = new MeteoAlertService(meteoAlertDao);
    TwitterClient twitterClient = new FakeTwitterClient();
    MeteoAlertCategoryMapper meteoAlertCategoryMapper = new MeteoAlertCategoryMapper();
    MeteoAlertMapper meteoAlertMapper = new MeteoAlertMapper(meteoAlertCategoryMapper);
    TweetService tweetService = new TweetService(meteoAlertService, twitterClient, meteoAlertMapper);

    @Test
    void shouldReturnMeteoTweetType(){
        //given
        final List<String> exampleOfHashtags = List.of("burze", "prognoza", "imgw");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(hashtags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.METEO);

    }

    @Test
    void shouldReturnMeteoAlertTweetType(){
        //given
        final List<String> exampleOfHashtags = List.of("burza", "ostrzeżenie", "imgw");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());
        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(hashtags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.METEO_ALERT);
    }

    @Test
    void shouldReturnOtherAlertTweetType(){
        //given
        final List<String> exampleOfHashtags = List.of("wiosna", "majówka", "imgw");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(hashtags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.OTHER);
    }

    @Test
    void tweetWithMeteoAlertShouldReturnTrue(){
        //given
        final List<String> exampleOfHashtags = List.of("burza", "ostrzeżenie", "imgw");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        Entity entity = new Entity();
        entity.setHashtags(hashtags);

        TweetDto tweetWithMeteoAlertHashTags = TweetDto.builder()
                .withId("1")
                .withText("test")
                .withAuthorId("1139834822011084801")
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withEntity(entity)
                .build();
        //when
        boolean meteoAlert = tweetService.isMeteoAlert(tweetWithMeteoAlertHashTags);

        // then
        assertThat(meteoAlert)
                .isTrue();
    }

    @Test
    void tweetWithoutMeteoAlertShouldReturnFalse(){
        //given
        final List<String> exampleOfHashtags = List.of("wiosna", "pogoda", "imgw");
        List<Hashtag> hashtags = exampleOfHashtags.stream()
                .map(Hashtag::new)
                .collect(Collectors.toList());

        Entity entity = new Entity();
        entity.setHashtags(hashtags);

        TweetDto tweetWithMeteoAlertHashTags = TweetDto.builder()
                .withId("1")
                .withText("test")
                .withAuthorId("1139834822011084801")
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withEntity(entity)
                .build();
        //when
        boolean notMeteoAlert = tweetService.isMeteoAlert(tweetWithMeteoAlertHashTags);

        // then
        assertThat(notMeteoAlert)
                .isFalse();
    }

}