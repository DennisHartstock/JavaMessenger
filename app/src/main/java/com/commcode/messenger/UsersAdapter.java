package com.commcode.messenger;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private OnUserClickListener onUserClickListener;

    private List<User> users = new ArrayList<>();

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_item,
                parent,
                false
        );
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText(user.getName());
        int bgResId = R.drawable.circle_green;
        //NullPointerException
//        if (user.isOnline()) {
//            bgResId = R.drawable.circle_green;
//        } else {
//            bgResId = R.drawable.circle_red;
//        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), bgResId);
        holder.viewOnlineStatus.setBackground(background);

        holder.itemView.setOnClickListener(view -> {
            if (onUserClickListener != null) {
                onUserClickListener.onUserClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    interface OnUserClickListener {

        void onUserClick(User user);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final View viewOnlineStatus;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            viewOnlineStatus = itemView.findViewById(R.id.viewOnlineStatus);
        }
    }
}
