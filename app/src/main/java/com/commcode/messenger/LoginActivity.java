package com.commcode.messenger;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogIn;
    private TextView tvForgetPassword;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        btLogIn.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            //login
        });

        tvForgetPassword.setOnClickListener(view -> {
            //launch intent to forget password screen
        });

        tvSignIn.setOnClickListener(view -> {
            //launch intent to register screen
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogIn = findViewById(R.id.btLogIn);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        tvSignIn = findViewById(R.id.tvSignIn);
    }
}