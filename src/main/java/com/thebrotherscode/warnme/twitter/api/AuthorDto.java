package com.thebrotherscode.warnme.twitter.api;

import com.fasterxml.jackson.annotation.JsonProperty;

class AuthorDto {

    private final String id;
    private final String name;
    private final String username;

//    public static AuthorDto fake() {
//        return new AuthorDto("1", "imgw", "imgw ipb");
//    }

    public AuthorDto(@JsonProperty("id") String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("username") String username) {
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
