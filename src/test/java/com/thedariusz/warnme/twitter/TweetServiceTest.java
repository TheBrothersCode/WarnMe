package com.thedariusz.warnme.twitter;

import com.thedariusz.warnme.MeteoAlertCategoryMapper;
import com.thedariusz.warnme.MeteoAlertDao;
import com.thedariusz.warnme.MeteoAlertMapper;
import com.thedariusz.warnme.MeteoAlertService;
import com.thedariusz.warnme.twitter.client.FakeTwitterClient;
import com.thedariusz.warnme.twitter.repository.InMemoryMeteoAlertDao;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        final List<String> exampleSetOfHashTags = List.of("burze", "prognoza", "imgw");
        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(exampleSetOfHashTags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.METEO);

    }

    @Test
    void shouldReturnMeteoAlertTweetType(){
        //given
        final List<String> exampleSetOfHashTags = List.of("burza", "ostrzeżenie", "imgw");
        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(exampleSetOfHashTags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.METEO_ALERT);
    }

    @Test
    void shouldReturnOtherAlertTweetType(){
        //given
        final List<String> exampleSetOfHashTags = List.of("wiosna", "majówka", "imgw");
        //when
        TweetService.TweetType tweetTypeBasedOnHashTags = tweetService.getTweetTypeBasedOnHashTags(exampleSetOfHashTags);

        // then
        assertThat(tweetTypeBasedOnHashTags)
                .isEqualTo(TweetService.TweetType.OTHER);
    }

    @Test
    void shouldReturnTrue(){
        //given
        TweetDto tweetWithMeteoAlertHashTags = TweetDto.builder()
                .withTweetId("1")
                .withText("test")
                .withAuthor(AuthorDto.fake("1139834822011084801"))
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withMediaList(List.of("url1", "url2"))
                .withHashTags(List.of("burze", "wichura", "ostrzeżenie"))
                .build();
        //when
        boolean meteoAlert = tweetService.isMeteoAlert(tweetWithMeteoAlertHashTags);

        // then
        assertThat(meteoAlert)
                .isTrue();
    }

    @Test
    void shouldReturnFalse(){
        //given
        TweetDto tweetWithMeteoAlertHashTags = TweetDto.builder()
                .withTweetId("1")
                .withText("test")
                .withAuthor(AuthorDto.fake("1139834822011084801"))
                .withCreationDate("2021-05-06T10:13:17.000Z")
                .withMediaList(List.of("url1", "url2"))
                .withHashTags(List.of("wiosna", "majówka", "imgw"))
                .build();
        //when
        boolean notMeteoAlert = tweetService.isMeteoAlert(tweetWithMeteoAlertHashTags);

        // then
        assertThat(notMeteoAlert)
                .isFalse();
    }

}