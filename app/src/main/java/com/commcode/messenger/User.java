package com.commcode.messenger;

public class User {

    private final String id;
    private final String name;
    private final Boolean isOnline;

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
}
