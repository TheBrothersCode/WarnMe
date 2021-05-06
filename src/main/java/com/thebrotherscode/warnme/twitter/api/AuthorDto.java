package com.thebrotherscode.warnme.twitter.api;

public class AuthorDto {
    private String id;
    private String name;
    private String username;

    public static AuthorDto fake() {
        return new AuthorDto("1", "imgw", "imgw ipb");
    }

    private AuthorDto(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
