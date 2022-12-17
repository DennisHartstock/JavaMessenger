package com.commcode.messenger;

import androidx.annotation.NonNull;

public class User {

    private String id;
    private String name;
    private Boolean isOnline;

    public User() {

    }

    public User(String id, String name, Boolean isOnline) {
        this.id = id;
        this.name = name;
        this.isOnline = isOnline;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isOnline() {
        return isOnline;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }
}
