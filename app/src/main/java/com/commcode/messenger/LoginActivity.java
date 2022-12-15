package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogIn;
    private TextView tvForgetPassword;
    private TextView tvSignUp;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        observeViewModel();
        setupClickListeners();
    }

    private void setupClickListeners() {
        btLogIn.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            loginViewModel.login(email, password);
        });

        tvForgetPassword.setOnClickListener(view -> startActivity(ResetPasswordActivity.newIntent(
                LoginActivity.this,
                etEmail.getText().toString().trim())
        ));

        tvSignUp.setOnClickListener(
                view -> startActivity(SignupActivity.newIntent(LoginActivity.this))
        );
    }

    private void observeViewModel() {
        loginViewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.getUser().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                startActivity(UsersActivity.newIntent(LoginActivity.this));
                finish();
            }
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogIn = findViewById(R.id.btLogIn);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}