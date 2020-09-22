package com.example.knack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserHelperClass> requests;

    public RequestsAdapter() {
    }

    public RequestsAdapter(Context context, ArrayList<UserHelperClass> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequestsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.single_requests_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsAdapter.MyViewHolder holder, int position) {
        holder.requesterName.setText(requests.get(position).getFullName());


    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView requesterName;
        Button accept,reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requesterName=itemView.findViewById(R.id.requesterName);
            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
        }
    }
}
