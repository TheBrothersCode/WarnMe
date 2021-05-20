package com.thebrotherscode.warnme.twitter.api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeTweetDao")
public class FakeTwitterDataAccess implements TweetDao {

    private static final List<Tweet> DB_TWEETS = new ArrayList<>();
    @Override
    public void save(Tweet tweet) {
        DB_TWEETS.add(tweet);
    }

    @Override
    public Optional<Tweet> findById(String id) {
        return DB_TWEETS.stream()
                .filter(tweet -> tweet.getTweetId().equals(id))
                .findFirst();
    }

    @Override
    public boolean exists(String tweetId) {
        for (Tweet tweet : DB_TWEETS) {
            if (tweet.getTweetId().equals(tweetId)) {
                return true;
            }
        }
        return false;
    }
}
