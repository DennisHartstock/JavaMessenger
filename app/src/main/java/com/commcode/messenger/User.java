package com.commcode.messenger;

import androidx.annotation.NonNull;

public class User {

    private String id;
    private String name;
    private Boolean online;

    public User() {

    }

    public User(String id, String name, Boolean online) {
        this.id = id;
        this.name = name;
        this.online = online;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isOnline() {
        return online;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isOnline=" + online +
                '}';
    }
}
