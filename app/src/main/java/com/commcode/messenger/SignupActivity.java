package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SignupActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;
    private Button btSignUp;

    private SignupViewModel signupViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();

        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        observeViewModel();

        btSignUp.setOnClickListener(view -> {
            String email = getTrimmedValue(etEmail);
            String password = getTrimmedValue(etPassword);
            String name = getTrimmedValue(etName);
            signupViewModel.signUp(email, password, name);
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        btSignUp = findViewById(R.id.btSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void observeViewModel() {
        signupViewModel.getError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(SignupActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        signupViewModel.getUser().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                startActivity(UsersActivity.newIntent(SignupActivity.this));
                finish();
            }
        });
    }
}