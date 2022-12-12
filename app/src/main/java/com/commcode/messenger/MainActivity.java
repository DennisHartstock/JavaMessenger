package com.commcode.messenger;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signInWithEmailAndPassword("dennis.hartstock@web.de", "111111")
//                .addOnFailureListener(e -> {
//                    Log.d(LOG_TAG, e.getMessage());
//                    Toast.makeText(MainActivity.this,
//                            e.getMessage(),
//                            Toast.LENGTH_SHORT).show();
//                });
//
//        firebaseAuth.addAuthStateListener(firebaseAuth -> {
//            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//            if (firebaseUser != null) {
//                Log.d(LOG_TAG, "Authorized " + firebaseUser.getUid());
//            } else {
//                Log.d(LOG_TAG, "Not authorized");
//            }
//        });
//        firebaseAuth.signOut();

        firebaseAuth.sendPasswordResetEmail("dennis.hartstock@web.de")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(LOG_TAG, "Email send");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG_TAG, e.getMessage());
                    }
                });
    }
}