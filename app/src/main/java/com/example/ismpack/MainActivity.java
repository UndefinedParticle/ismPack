package com.example.ismpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button registerButton,loginButton;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    TextView skp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        /*skp=findViewById(R.id.skip);
        skp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });*/

        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        registerButton=findViewById(R.id.newRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity((intent1));
            }
        });

        loginButton=findViewById(R.id.newLogIn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,LogInActivity.class);
                startActivity((intent1));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser != null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }
    }
}