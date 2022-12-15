package com.commcode.messenger;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsersViewModel extends ViewModel {

    private final FirebaseAuth auth;

    private final MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    public UsersViewModel() {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(firebaseAuth -> user.setValue(firebaseAuth.getCurrentUser()));
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public void logout() {
        auth.signOut();
    }
}
