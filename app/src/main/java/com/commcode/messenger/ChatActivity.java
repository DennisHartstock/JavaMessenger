package com.commcode.messenger;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {

    private TextView tvTitle;
    private View viewOnlineStatus;
    private RecyclerView rvMessages;
    private EditText etMessage;
    private ImageView ivSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        viewOnlineStatus = findViewById(R.id.viewOnlineStatus);
        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        ivSendMessage = findViewById(R.id.ivSendMessage);
    }
}