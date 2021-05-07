package com.thebrotherscode.warnme.twitter.api;

class AuthorDto {

    private final String id;
    private final String name;
    private final String username;

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

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

}
