package com.example.knack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<UserHelperClass> profiles;
    DatabaseReference databaseReference;
    FirebaseRecycleAdapter firebaseRecycleAdapter;
    View view;
    public HomeFragment(){


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.homefragment,container,false);
         recyclerView=view.findViewById(R.id.recyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        profiles=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profiles.clear();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                        UserHelperClass p=dataSnapshot.getValue(UserHelperClass.class);
                        profiles.add(p);
                        System.out.println("iinsidee in");
                    }
                System.out.println("ia dcdsvdsfvvf "+profiles.toString());
                    firebaseRecycleAdapter=new FirebaseRecycleAdapter(getContext(),profiles);
                    recyclerView.setAdapter(firebaseRecycleAdapter);
                firebaseRecycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Ooops..something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

