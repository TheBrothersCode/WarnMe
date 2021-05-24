package com.thedariusz.warnme.twitter;

public class AuthorDto {

    private final String id;
    private final String name;
    private final String username;

    public static AuthorDto fake(String twitterUserId) {
        return new AuthorDto(twitterUserId, "imgw", "imgw ipb");
    }

    private AuthorDto(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

}
