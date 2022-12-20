package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final String EXTRA_CURRENT_USER_ID = "current_id";
    private static final String EXTRA_OTHER_USER_ID = "other_id";

    private TextView tvTitle;
    private View viewOnlineStatus;
    private RecyclerView rvMessages;
    private EditText etMessage;
    private ImageView ivSendMessage;

    private MessagesAdapter messagesAdapter;

    private String currentUserId;
    private String otherUserId;

    private ChatViewModel viewModel;

    public static Intent newIntent(Context context, String currentUserId, String otherUserId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID, currentUserId);
        intent.putExtra(EXTRA_OTHER_USER_ID, otherUserId);
        return intent;
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        viewOnlineStatus = findViewById(R.id.viewOnlineStatus);
        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        ivSendMessage = findViewById(R.id.ivSendMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();

        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);
        otherUserId = getIntent().getStringExtra(EXTRA_OTHER_USER_ID);
        messagesAdapter = new MessagesAdapter(currentUserId);
        rvMessages.setAdapter(messagesAdapter);

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message myMessage = new Message(
                    "My message " + i,
                    currentUserId,
                    otherUserId
            );
            messages.add(myMessage);
        }

        for (int i = 0; i < 10; i++) {
            Message otherMessage = new Message(
                    "Other message " + i,
                    otherUserId,
                    currentUserId
            );
            messages.add(otherMessage);
        }
        messagesAdapter.setMessages(messages);
    }
}