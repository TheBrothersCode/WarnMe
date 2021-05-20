package com.thebrotherscode.warnme.twitter.api;

import java.util.Optional;

public interface TweetDao {
    void save(Tweet tweet);
    Optional<Tweet> findById(String id);
    boolean exists(String id);
}
