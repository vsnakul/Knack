package com.example.knack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.pager);
        fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ProfileFragment());
        fragments.add(new MessagesFragment());
        FragmentAdapter fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager(),getApplicationContext(),fragments);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Home");
        tabLayout.getTabAt(1).setText("Profile");
        tabLayout.getTabAt(2).setText("Messages");


    }


    };
