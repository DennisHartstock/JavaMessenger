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
        viewModel.getUsers().observe(this, users -> usersAdapter.setUsers(users));
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