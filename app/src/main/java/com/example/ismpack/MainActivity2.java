package com.example.ismpack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ismpack.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    FirebaseAuth mAuth;

private WebView web1;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Objects.requireNonNull(getSupportActionBar()).hide();

        mAuth=FirebaseAuth.getInstance();

        web1=(WebView) findViewById(R.id.home);
        WebSettings webSettings1=web1.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        web1.loadUrl("https://www.iitism.ac.in/iitismnew/");
        if(web1.getUrl().endsWith(".pdf")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web1.getUrl())));
        } else{
            web1.setWebViewClient(new WebViewController());
        }

        //web1.setWebViewClient(new WebViewController());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menubar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "CLicked Settngs", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logOut:
                mAuth.signOut();
                startActivity(new Intent(MainActivity2.this,RegisterActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if(web1!= null && web1.canGoBack())
            web1.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }
}