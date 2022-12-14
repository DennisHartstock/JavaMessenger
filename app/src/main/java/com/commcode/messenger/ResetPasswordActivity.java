package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";

    private EditText etEmail;
    private Button btResetPassword;

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();
        String receivedEmail = getIntent().getStringExtra(EXTRA_EMAIL);
        etEmail.setText(receivedEmail);

        btResetPassword.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        btResetPassword = findViewById(R.id.btResetPassword);
    }
}