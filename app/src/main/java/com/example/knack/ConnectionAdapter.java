package com.example.knack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserHelperClass> connections;
    String currentUser;

    public ConnectionAdapter(Context context, ArrayList<UserHelperClass> connections,String currentUser) {
        this.context = context;
        this.connections = connections;
        this.currentUser=currentUser;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_connection_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.connectionName.setText(connections.get(position).getFullName());
        System.out.println("inside adapter "+connections.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return connections.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView connectionName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            connectionName=itemView.findViewById(R.id.connectionName);

        }
    }
}
