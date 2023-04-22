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

        webSettings1.setBuiltInZoomControls(true);
        webSettings1.setDisplayZoomControls(false);
        webSettings1.setSupportZoom(true);
        webSettings1.setUseWideViewPort(true);
        webSettings1.setLoadWithOverviewMode(true);
        webView.setInitialScale(1);

        webView.loadUrl("https://amansoni1.github.io/Pokem.github.io/");


        return view;
    }
}