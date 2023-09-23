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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private EditText Rname,Remail,Rpassword;
    private Button register;
    private String name,email,password;
    private FirebaseAuth auth;
    private DatabaseReference reference,dbRef;
    private TextView openLog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        Rname = findViewById(R.id.user_name);
        Remail = findViewById(R.id.user_email);
        Rpassword = findViewById(R.id.user_password);
        register = findViewById(R.id.register);
        openLog = findViewById(R.id.openLog);

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void openLogin() {
        startActivity(new Intent(Register.this,Login.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() !=null){
            openMain();
        }
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void validateData() {
        name = Rname.getText().toString();
        email = Remail.getText().toString();
        password = Rpassword.getText().toString();

        if(name.isEmpty()){
            Rname.setError("Required");
            Rname.requestFocus();
        }else if(email.isEmpty()){
            Remail.setError("Required");
            Remail.requestFocus();
        }else if(password.isEmpty()){
            Rpassword.setError("Required");
            Rpassword.requestFocus();
        }else{
            createUser();
        }
    }

    private void createUser() {
      auth.createUserWithEmailAndPassword(email,password)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          uploadData();
                      }
                      else {
                          Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(Register.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
    }

    private void uploadData() {
        dbRef = reference.child("user");
        String key = dbRef.push().getKey();

        HashMap<String,String> user = new HashMap<>();
        user.put("key",key);
        user.put("name",name);
        user.put("email",email);
        user.put("status","no");

        dbRef.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Create Account", Toast.LENGTH_SHORT).show();
                            openMain();
                        }
                        else {
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}