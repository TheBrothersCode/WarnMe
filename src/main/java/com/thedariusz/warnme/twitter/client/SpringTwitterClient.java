package com.thedariusz.warnme.twitter.client;

import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.TweetDtoWrapper;
import com.thedariusz.warnme.twitter.TwitterClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Map;

public class SpringTwitterClient implements TwitterClient {

    private final WebClient webClient;

    public SpringTwitterClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public TweetDtoWrapper fetchAllTweets(String twitterUserId) {
//        Map<String, String> queryParamMap = Map.of(
//                "max_results", "20",
//                "expansions", "attachments.media_keys",
//                "tweet.fields", "author_id,created_at,entities,id,text",
//                "media.fields", "type,url,width,height,media_key"
//        );
        return webClient.get()
                .uri("/users/" + twitterUserId + "/tweets?max_results=40&expansions=attachments.media_keys" +
                        "&tweet.fields=author_id,created_at,entities,id,text" +
                        "&media.fields=type,url,width,height,media_key")
//                .uri("/users/" + twitterUserId +"/tweets",queryParamMap)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(TweetDtoWrapper.class)
                .block();
    }

    @Override
    public TweetDto getSingleTweetDto(String tweetId) {

        TweetDtoWrapper tweetDtoWrapper = webClient.get()
                .uri("/tweets/" + tweetId + "?tweet.fields=created_at,id,text")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(TweetDtoWrapper.class)
                .block();
        return tweetDtoWrapper.getData().get(0);
    }

}
