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

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private String emailText;
    private EditText password;
    private String passwordText;
    private Button signIn;
    private Button newUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
        newUser = (Button) findViewById(R.id.newUserButton);
        mAuth.signOut();//makes sure pre user is signed out
        mAuthSL = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    Intent i  = new Intent();
                    i.putExtra("email", emailText);
                    setResult(1,i);
                    finish();
                }
            }
        };

        /*mDatabase.child("account").child("username").setValue(e);
                mDatabase.child("account").child("password").setValue(p);
                finish();*/

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
                }
            });
        }
    }

    private void startSignIn() {
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
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
