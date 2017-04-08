package com.tannerowens.a407_roommate_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private String emailText;
    private EditText password;
    private String passwordText;
    private EditText nameText;
    private String name;
    private Button signIn;
    private Button newUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthSL;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://roommateapp-a6d3a.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        nameText = (EditText) findViewById(R.id.nameText);
        signIn = (Button) findViewById(R.id.signIn);
        newUser = (Button) findViewById(R.id.newUserButton);
        //mAuth.signOut();//makes sure pre user is signed out
        mAuthSL = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    Intent i  = new Intent();
                    i.putExtra("email", emailText);
                    i.putExtra("name", name);
                    setResult(1,i);
                    finish();
                }
            }
        };

        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startSignIn();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startNewUser();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthSL);
    }

    private  void startNewUser(){
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        name = nameText.getText().toString();
        if (TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(emailText)) {
            Toast.makeText(SignUpActivity.this, "Fill in email and password", Toast.LENGTH_LONG).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "New User Sign up Failed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        String[] splitEmail = emailText.split("@");
                        String username = splitEmail[0];
                        User newUser = new User(name, emailText);
                        mDatabase.child("users").child(username.toString()).setValue(newUser);
                    }
                }
            });
        }
    }

    private void startSignIn() {
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        name = nameText.getText().toString();
        if (TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(emailText)) {
            Toast.makeText(SignUpActivity.this, "Fill in email AND password", Toast.LENGTH_LONG).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Email or Password incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
