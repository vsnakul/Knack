package com.example.knack;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileFragment extends Fragment {
    View view;
    AppCompatEditText skill1,skill2,skill3,skill4,skill5;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ArrayList<AppCompatEditText> totalSkillCards;
    ArrayList<String> userSkillList;
    ArrayList<AppCompatEditText> cards;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.profilefragment,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init(view);
        skillSetColorChange(skill1);
        skillSetColorChange(skill2);
        skillSetColorChange(skill3);
        skillSetColorChange(skill4);
        skillSetColorChange(skill5);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).child("skills");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userSkillList.clear();
                cards.clear();
                String userSkills=snapshot.getValue().toString();
                System.out.println("user skills are "+userSkills);
                userSkillList=convertToList(userSkills);

                cards=arrangeCardForStoringSkills(userSkillList);
                for (int i=0;i<cards.size();i++){
                    cards.get(i).setText(userSkillList.get(i));
                }
                makeSkillCardVisible(userSkillList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void init(View view){
        skill1=view.findViewById(R.id.skill1);
        skill2=view.findViewById(R.id.skill2);
        skill3=view.findViewById(R.id.skill3);
        skill4=view.findViewById(R.id.skill4);
        skill5=view.findViewById(R.id.skill5);
        totalSkillCards=new ArrayList<>();
        userSkillList=new ArrayList<>();
        cards=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        totalSkillCards.add(skill1);
        totalSkillCards.add(skill2);
        totalSkillCards.add(skill3);
        totalSkillCards.add(skill4);
        totalSkillCards.add(skill5);

    }
    public void skillSetColorChange(final AppCompatEditText appCompatEditText){
        appCompatEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ColorStateList colorStateList=ColorStateList.valueOf(Color.parseColor("#094067"));
                    appCompatEditText.setSupportBackgroundTintList(colorStateList);
                }
                else{
                    ColorStateList colorStateList=ColorStateList.valueOf(Color.parseColor("#ffffff"));
                    appCompatEditText.setSupportBackgroundTintList(colorStateList);
                }
            }
        });

    }
    public ArrayList<String> convertToList(String a){
        String[] split=a.split(",");
        ArrayList<String> list=new ArrayList<>(Arrays.asList(split));
        return  list;
    }
    public ArrayList<AppCompatEditText> arrangeCardForStoringSkills(ArrayList<String> list){
        ArrayList<AppCompatEditText> arrangedCards=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            arrangedCards.add(totalSkillCards.get(i));
        }
        return  arrangedCards;
    }
    public void makeSkillCardVisible(ArrayList<String> list){
        int j=0;
        for (int i=0;i<list.size();i++){
            totalSkillCards.get(i).setVisibility(View.VISIBLE);
            j=i;
        }
        if(list.size()!=5){

            for (int k=j+1;k<totalSkillCards.size();k++){
                totalSkillCards.get(k).setVisibility(View.INVISIBLE);
            }
        }
    }
}
