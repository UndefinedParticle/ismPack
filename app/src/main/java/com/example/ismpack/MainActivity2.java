package com.example.ismpack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Objects;


public class MainActivity2 extends AppCompatActivity {
private WebView web1;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Objects.requireNonNull(getSupportActionBar()).hide();

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
    public void onBackPressed() {
        if(web1!= null && web1.canGoBack())
            web1.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }
}