package com.thedariusz.warnme.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDto {
    @NotEmpty(message = "Please enter a user name")
    @Size(min = 8, message = "Name couldn't be empty and should have at least 8 letters")
    private String username;

    @Size(min = 8, message = "Password couldn't be empty and should have at least 8 letters")
    private String password;

    private int enabled;
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(String username, String password, int enabled, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
