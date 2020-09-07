package com.example.knack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemReselectedListener(bottomlistener);
    }
    BottomNavigationView.OnNavigationItemReselectedListener bottomlistener=new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.home:{
                    selectedFragment=new HomeFragment();
                    break;

                }
                case  R.id.messages:{
                    selectedFragment=new MessagesFragment();
                    break;
                }
                case R.id.profile:{
                    selectedFragment=new ProfileFragment();
                    break;
                }

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,selectedFragment).commit();
        }

    };
}
