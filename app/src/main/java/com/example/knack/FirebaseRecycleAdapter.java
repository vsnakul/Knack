package com.example.knack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirebaseRecycleAdapter extends RecyclerView.Adapter<FirebaseRecycleAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserHelperClass
            > list;

    public FirebaseRecycleAdapter(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_profile_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullName.setText(list.get(position).getFullName());
        holder.email.setText(list.get(position).getEmail());
        holder.phone.setText(list.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email,phone,fullName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.userEmail);
            phone=itemView.findViewById(R.id.userphone);
            fullName=itemView.findViewById(R.id.username);
        }
    }
}
