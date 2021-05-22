package com.thedariusz.warnme.twitter.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@WebClient
@Component
public class TwitterClient {

    public List<TweetDto> fetchAllTweets(String twitterUserId) {
        List<TweetDto> tweetDtos = new ArrayList<>();
        String tweetText1 = "Woj. śląskie ostrzeżenie 2 stopnia - Burze z gradem ❗️⚡️\\n\\n➡️Prognozuje się wystąpienie burz z opadami " +
                "deszczu miejscami od 30 mm do 50 mm, lokalnie do\\n60 mm oraz porywami wiatru do 80 km/h. " +
                "\\nMiejscami grad. \\n\\n#IMGW #ostrzeżenie #burze #podtopienia https://t.co/vaYBh6XRaX";

        String tweetText2 = "⚠️IMGW-PIB wydał #ostrzeżenia meteo 1° przed burzami z gradem dla woj. dolnośląskiego, " +
                "opolskiego, południowej części woj. małopolskiego, podkarpackiego i śląskiego. " +
                "Prognozuje się wystąpienie burz z opadami deszczu miejscami do 40 mm oraz porywami wiatru do 80 km/h.\n#burze #IMGW https://t.co/dpkOHtEM9y";


        if ("1139834822011084801".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.fakeTweet("1", "2021-05-06T10:13:17.000Z", twitterUserId, List.of("ostrzeżenie", "podtopienia", "burze"), tweetText1 ),
                    TweetDto.fakeTweet("2", "2021-04-06T11:00:01.000Z", twitterUserId, List.of("ostrzeżenia", "burze", "IMGW"), tweetText2),
                    TweetDto.fakeTweet("3", "2021-05-10T22:54:44.000Z", twitterUserId, List.of("wiosna"), "testowy")
            );
        }
        if ("2979632800".equals(twitterUserId)) {
            tweetDtos =  List.of(
                    TweetDto.fakeTweet("12", "2021-05-11T11:10:10.000Z", twitterUserId, List.of("burze", "alertrcb"), "testowy"),
                    TweetDto.fakeTweet("2", "2021-03-12T17:05:02.000Z", twitterUserId, List.of("upał", "alert"), "testowy"),
                    TweetDto.fakeTweet("3", "2021-02-09T08:55:40.000Z", twitterUserId, List.of("strajk"), "testowy")
            );
        }

        return tweetDtos;
    }
}
