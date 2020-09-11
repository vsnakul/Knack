package com.example.knack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button callSignup,signin;
    ImageView knack_logo;
    TextView greeting,instruction;
    TextInputLayout email,password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();
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
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        callSignup=findViewById(R.id.signincall);
        signin=findViewById(R.id.signin);
        knack_logo=findViewById(R.id.logo_image);
        knack_logo.setImageResource(R.drawable.logo_blue_down);
        greeting=findViewById(R.id.greeting);
        instruction=findViewById(R.id.instruction);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        callSignup=findViewById(R.id.signincall);
        callSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                /*Pair[] pairs=new Pair[7];
                pairs[0]=new Pair<View,String>(knack_logo,"logo_img");
                pairs[1]=new Pair<View,String>(greeting,"greeting_tran");
                pairs[2]=new Pair<View,String>(instruction,"instruction_tran");
                pairs[3]=new Pair<View,String>(username,"username_trans");

                pairs[4]=new Pair<View,String>(password,"password_trans");
                pairs[5]=new Pair<View,String>(signin,"gobtn_trans");
                pairs[6]=new Pair<View,String>(callSignup,"signup_btn_tran");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }*/
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateEmail() | !validatePassword())
                    return;
                mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    if(mAuth.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(LoginActivity.this, "SIgned in succesfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        FirebaseUser user = mAuth.getCurrentUser();
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, "Please Verify your email", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LoginActivity.this, "Authentication failed."+task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });



    }
    protected boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    protected boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

}}
