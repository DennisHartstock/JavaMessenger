package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;

    private UsersViewModel viewModel;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        for (int i = 0; i < 10; i++) {
            User user = new User("id" + i, "name" + i, false);
            databaseReference.push().setValue(user);
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User value = dataSnapshot.getValue(User.class);
                    Log.d("UsersActivity", String.valueOf(value));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("UsersActivity", "Failed to read value " + error.toException());
            }
        });

        initViews();
        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        observeViewModel();
    }

    private void initViews() {
        rvUsers = findViewById(R.id.rvUsers);
        usersAdapter = new UsersAdapter();
        rvUsers.setAdapter(usersAdapter);
    }

    private void observeViewModel() {
        viewModel.getUser().observe(this, firebaseUser -> {
            if (firebaseUser == null) {
                startActivity(LoginActivity.newIntent(UsersActivity.this));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_logout) {
            viewModel.logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, UsersActivity.class);
    }
}