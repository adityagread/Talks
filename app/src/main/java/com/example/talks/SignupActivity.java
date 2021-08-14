package com.example.talks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore database;
    EditText emailtext, passwordtext, nametext;
    Button createbutton, alreadybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailtext = findViewById(R.id.emailtext);
        passwordtext = findViewById(R.id.passwordtext);
        nametext = findViewById(R.id.nametext);
        createbutton = findViewById(R.id.createbutton);
        alreadybutton = findViewById(R.id.alreadybutton);


        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, name;
                email = emailtext.getText().toString();
                password = passwordtext.getText().toString();
                name = nametext.getText().toString();

                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //success
                            database.collection("Users")
                                    .document().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                }
                            });
                            Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        alreadybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}