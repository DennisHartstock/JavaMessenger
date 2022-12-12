package com.commcode.messenger;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Log.d(LOG_TAG, "Authorized " + firebaseUser.getUid());
        } else {
            Log.d(LOG_TAG, "Not authorized");
        }
        firebaseAuth.signOut();
//        firebaseAuth.createUserWithEmailAndPassword("email@web.de", "111111")
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                if (firebaseUser != null) {
//                    Log.d(LOG_TAG, "Authorized");
//                } else {
//                    Log.d(LOG_TAG, "Not authorized");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(LOG_TAG, e.getMessage());
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}