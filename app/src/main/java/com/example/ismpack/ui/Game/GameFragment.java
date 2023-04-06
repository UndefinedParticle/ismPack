package com.example.ismpack.ui.Game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.ismpack.R;
import com.example.ismpack.WebViewController;


public class GameFragment extends Fragment {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        webView = view.findViewById(R.id.GameWebView);
        WebSettings webSettings1=webView.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webView.loadUrl("https://amansoni1.github.io/Pokem.github.io/");
        if(webView.getUrl().endsWith(".pdf")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl())));
        } else{
            webView.setWebViewClient(new WebViewController());
        }

        return view;
    }
}