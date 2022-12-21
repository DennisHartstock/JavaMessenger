package com.commcode.messenger;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private final MutableLiveData<List<Message>> messages = new MutableLiveData<>();
    private final MutableLiveData<User> otherUser = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isMessageSent = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference referenceUsers = firebaseDatabase.getReference("Users");
    private final DatabaseReference referenceMessages = firebaseDatabase.getReference("Messages");

    private final String currentUserId;
    private final String otherUserId;

    public ChatViewModel(String currentUserId, String otherUserId) {
        this.currentUserId = currentUserId;
        this.otherUserId = otherUserId;
        referenceUsers.child(otherUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                otherUser.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceMessages.child(currentUserId).child(otherUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Message> messageList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    messageList.add(message);
                }
                messages.setValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public LiveData<User> getOtherUser() {
        return otherUser;
    }

    public LiveData<Boolean> getIsMessageSent() {
        return isMessageSent;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void setUserOnline(Boolean isOnline) {
        referenceUsers.child(currentUserId).child("online").setValue(isOnline);
    }

    public void sendMessage(Message message) {
        referenceMessages
                .child(message.getSenderId())
                .child(message.getReceiverId())
                .push()
                .setValue(message)
                .addOnSuccessListener(unused -> referenceMessages
                        .child(message.getReceiverId())
                        .child(message.getSenderId())
                        .push()
                        .setValue(message)
                        .addOnSuccessListener(unused1 -> isMessageSent.setValue(true))
                        .addOnFailureListener(e -> error.setValue(e.getMessage())))
                .addOnFailureListener(e -> error.setValue(e.getMessage()));
    }
}
