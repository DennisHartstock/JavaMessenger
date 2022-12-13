package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etEmailSignup;
    private EditText etPasswordSignup;
    private EditText etNameSignup;
    private Button btSignUp;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();

        btSignUp.setOnClickListener(view -> {
            String emailSignup = getTrimmedValue(etEmailSignup);
            String passwordSignup = getTrimmedValue(etPasswordSignup);
            String nameSignup = getTrimmedValue(etNameSignup);
            //sign up
        });
    }

    private void initViews() {
        etEmailSignup = findViewById(R.id.etEmailSignup);
        etPasswordSignup = findViewById(R.id.etPasswordSignup);
        etNameSignup = findViewById(R.id.etNameSignup);
        btSignUp = findViewById(R.id.btSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }
}