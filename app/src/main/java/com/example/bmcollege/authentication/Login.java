package com.example.bmcollege.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmcollege.MainActivity;
import com.example.bmcollege.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView openReg,openForget;
    private EditText login_email, login_password;
    private Button loginButton;
    private String email,password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openReg = findViewById(R.id.openReg);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.loginButton);
        openForget = findViewById(R.id.openForget);

        auth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgetPassword();
            }
        });

        openReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    private void validateData() {
        email = login_email.getText().toString();
        password = login_password.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fill this", Toast.LENGTH_SHORT).show();
        }
        else{
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               openMain();
                           }
                           else {
                               Toast.makeText(Login.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openMain() {
        startActivity(new Intent(Login.this, MainActivity.class));

    }

    private void openForgetPassword() {
        startActivity(new Intent(Login.this,ForgetPassword.class));
    }

    private void openRegister() {
        startActivity(new Intent(Login.this,Register.class));
        finish();
    }

}