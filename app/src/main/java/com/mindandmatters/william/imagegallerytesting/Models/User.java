package com.mindandmatters.william.imagegallerytesting.Models;

/**
 * Created by lappy on 2018-04-28.
 */

public class User {

    private String user_id, username, email;
    private long phone_number;

    public User( ) {
    }

    public User(String user_id, long phone_number, String username, String email) {
        this.user_id = user_id;
        this.username = username;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
