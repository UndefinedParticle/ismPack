package com.example.ismpack;

import static android.os.Build.ID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ismpack.Models.Users;
import com.example.ismpack.databinding.ActivityRegisterBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    BeginSignInRequest signInRequest;
    private SignInClient oneTapClient;


    Button finalRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();



        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build()).build();


        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        binding.googleLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startIntentSenderForResult(signInRequest.getPendingIntent().getIntentSender(), REQ_ONE_TAP, null, 0, 0, 0);
            }
        });




        binding.finalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(binding.EmailAddress.getText().toString(),binding.Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Users user=new Users(binding.PersonName.getText().toString(),binding.admissionNumber.getText().toString(),binding.EmailAddress.getText().toString(),binding.Password.getText().toString());

                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);

                            Toast.makeText(RegisterActivity.this, "User created Successfully", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                startActivity(new Intent(RegisterActivity.this,MainActivity2.class));
            }
        });

        /*if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(RegisterActivity.this,MainActivity2.class));
        }*/


    }


    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    // ...

    private void signIn() {
        //Intent signInIntent = signInRequest.getSignInIntent();
        //startActivityForResult(signInIntent, REQ_ONE_TAP);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with Firebase.
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("TAG", "signInWithCredential:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            //updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                                            //updateUI(null);
                                        }
                                    }
                                });
                        Log.d("TAG", "Got ID token.");
                    }
                } catch (ApiException e) {
                    Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
                }
                break;
        }
    }




}