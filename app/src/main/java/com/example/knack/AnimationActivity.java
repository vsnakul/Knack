package com.example.knack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.InvalidMarkException;

public class AnimationActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Fade fade=new Fade();
            View decor=getWindow().getDecorView();
            fade.excludeTarget(android.R.id.statusBarBackground,true);
            fade.excludeTarget(android.R.id.navigationBarBackground,true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(fade);
                getWindow().setEnterTransition(fade);
            }
        }*/
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo=findViewById(R.id.app_logo);
        logo.setImageResource(R.drawable.l_white_down);
        logo.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(AnimationActivity.this,LoginActivity.class);
               /* Pair[] pairs=new Pair[1];
                pairs[0]=new Pair<View,String>(logo,"logo_img");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(AnimationActivity.this,logo, ViewCompat.getTransitionName(logo));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent,options.toBundle());

                }*/
                startActivity(intent);
                overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
                finish();
            }
        },3500);

            }
}
