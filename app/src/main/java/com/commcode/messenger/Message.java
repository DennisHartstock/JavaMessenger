package com.commcode.messenger;

public class Message {

    private String message;
    private String senderId;
    private String receiverId;

    public Message(String message, String senderId, String receiverId) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Message() {

    }

    public String getMessage() {
        return message;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }
}
