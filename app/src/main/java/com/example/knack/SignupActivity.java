package com.example.knack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextInputLayout fullName,userName,password,confirmPassword,phone,email;
    Button signup,signincall;
    FirebaseAuth mAuth;
    boolean isAccountVerified=false;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    UserHelperClass userHelperClass;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fullName=findViewById(R.id.fullname);
        userName=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmpassword);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        signup=findViewById(R.id.signupbtn);
        signincall=findViewById(R.id.signincall);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateFullname() | !validatePassword() |!validatePhone() |!validateUsername() | !validateEmail()){
                    return;
                }
                else if(!confirmPassword.getEditText().getText().toString().equals(password.getEditText().getText().toString())){
                    Toast.makeText(SignupActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                    password.getEditText().setText("");
                    confirmPassword.getEditText().setText("");
                }
                else{
                    createAccount(mAuth);
                }

            }
        });
        signincall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });


       /* Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.white));

        }*/


    }

    private void createAccount(final FirebaseAuth mAuth) {
        mAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString(),password.getEditText().getText().toString())
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignupActivity.this, "Email sent succesfully", Toast.LENGTH_SHORT).show();
                                    firebaseDatabase=FirebaseDatabase.getInstance();
                                    databaseReference=firebaseDatabase.getReference("users");
                                    firebaseUser=mAuth.getCurrentUser();
                                    String userId=firebaseUser.getUid();
                                    System.out.println("id of the user is "+userId);
                                     userHelperClass=new UserHelperClass(fullName.getEditText().getText().toString()
                                             ,email.getEditText().getText().toString(),phone.getEditText().getText().toString(),userId,isAccountVerified,null,null,null);
                                    databaseReference.child(userId).setValue(userHelperClass);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("Unable to sent email");
                                    Toast.makeText(SignupActivity.this, "Unfortunately "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                           /* mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }

                                @Override
                                public void onSuccess(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "User Created and Verify your email", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(SignupActivity.this, "User Creation Failed beacuse"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });*/

                        }
                        else{

                            Toast.makeText(SignupActivity.this, "User Creation Failed beacuse"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected boolean validateUsername(){
        String val = userName.getEditText().getText().toString();
        String noWhiteSpace = "^[a-zA-Z0-9._-]{3,}$";

        if (val.isEmpty()) {
            userName.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            userName.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            userName.setError("White Spaces are not allowed");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
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
    }
    private boolean validateFullname(){
        String value=fullName.getEditText().getText().toString();
        String regex="^[a-zA-Z][a-zA-Z\\-' ]*[a-zA-Z ]$";
        if(!value.matches(regex)){
            fullName.setError("Name cannot contain a digit");
            return false;
        }
        else if(value.isEmpty()){
            fullName.setError("Field cannot be empty");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhone(){
        String val = phone.getEditText().getText().toString();

        if (val.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }

    }
    private boolean validateEmail(){
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
    private boolean validateConfirmPassword(){
        String val = confirmPassword.getEditText().getText().toString();
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
            confirmPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            confirmPassword.setError("Password is too weak");
            return false;
        } else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
   


}
