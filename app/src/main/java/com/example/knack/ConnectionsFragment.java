package com.example.knack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConnectionsFragment extends Fragment {
    View view;
    RecyclerView connectionsRecyclerView,requestsRecyclerView;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ArrayList<UserHelperClass> connections;

    ArrayList<UserHelperClass> requests;
    ConnectionAdapter connectionAdapter;
    UserHelperClass p;
    RequestsAdapter requestsAdapter;
    ConnectionsFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.connectionfragment,container,false);
        firebaseAuth=FirebaseAuth.getInstance();

        connectionsRecyclerView =view.findViewById(R.id.connectionRecycler);
        connectionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        requestsRecyclerView=view.findViewById(R.id.requestRecycler);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        connectionsPopulator();
        requestsPopulator();


    }
    public void connectionsPopulator(){
        connections=new ArrayList<>();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).child("connections");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int i=0;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    final String connection=snapshot.child(String.valueOf(i)).getValue(String.class);
                    i++;
                    System.out.println("here is the fetched data ");
                    databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(connection);
                    databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            connections.clear();
                            System.out.println("user node data here "+ snapshot.toString());
                            p=snapshot.getValue(UserHelperClass.class);
                            connections.add(p);
                            System.out.println(connections.toString());

                            System.out.println("Using pojo "+connections.get(0).getConnections());

                            System.out.println("Using pojo list"+connections.get(0).getEmail());

                            connectionAdapter=new ConnectionAdapter(getContext(),connections,firebaseAuth.getCurrentUser().getUid());
                            connectionsRecyclerView.setAdapter(connectionAdapter);
                            connectionAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                // connections.add(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void requestsPopulator(){
        requests=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).child("requests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int i=0;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    final String request=snapshot.child(String.valueOf(i)).getValue(String.class);
                    i++;
                    System.out.println("here is the fetched data ");
                    databaseReference=FirebaseDatabase.getInstance().getReference().child("users").child(request);
                    databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            requests.clear();
                            System.out.println("fxdf"+ snapshot.toString());
                            p=snapshot.getValue(UserHelperClass.class);
                            requests.add(p);
                            System.out.println(requests.toString());
                            System.out.println("Using pojo "+p.getFullName());

                            System.out.println("Using pojo "+requests.get(0).getConnections());

                            System.out.println("Using pojo list"+requests.get(0).getEmail());
                            requestsAdapter= new RequestsAdapter(getContext(),requests);
                            requestsRecyclerView.setAdapter(requestsAdapter);
                            requestsAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                // connections.add(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}

