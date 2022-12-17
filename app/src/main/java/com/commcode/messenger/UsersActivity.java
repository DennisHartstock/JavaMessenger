package com.commcode.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;

    private UsersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        initViews();
        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        observeViewModel();

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User("id " + i, "name " + i, new Random().nextBoolean());
            users.add(user);
        }
        usersAdapter.setUsers(users);
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