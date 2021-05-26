package com.thedariusz.warnme.twitter.client;

import com.thedariusz.warnme.twitter.TweetDto;
import com.thedariusz.warnme.twitter.TwitterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.List;

public class SpringTwitterClient implements TwitterClient {

    @Autowired
    private final WebClient webClient;

    public SpringTwitterClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<TweetDto> fetchAllTweets(String twitterUserId) {
        return List.of();
    }

    @Override
    public Disposable getSingleTweet(String tweetId) {
        return webClient.get()
                .uri("/tweets/" + tweetId + "?tweet.fields=attachments,author_id,context_annotations,created_at,entities,geo,id,lang,source,text")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(String.class)
                .subscribe((body)-> { System.out.println("body:" + body);} );
    }

}
