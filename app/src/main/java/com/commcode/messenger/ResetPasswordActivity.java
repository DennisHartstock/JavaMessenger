package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";

    private EditText etEmail;
    private Button btResetPassword;

    private ResetPasswordViewModel resetPasswordViewModel;

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

        resetPasswordViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeViewModel();

        String receivedEmail = getIntent().getStringExtra(EXTRA_EMAIL);
        etEmail.setText(receivedEmail);

        btResetPassword.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            resetPasswordViewModel.resetPassword(email);
        });
    }

    private void observeViewModel() {
        resetPasswordViewModel.isSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(ResetPasswordActivity.this,
                        R.string.toast_reset_link_sent,
                        Toast.LENGTH_SHORT).show();
            }
        });
        resetPasswordViewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(ResetPasswordActivity.this,
                        errorMessage,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        btResetPassword = findViewById(R.id.btResetPassword);
    }
}