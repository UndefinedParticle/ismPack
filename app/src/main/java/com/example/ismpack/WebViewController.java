package com.example.ismpack;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.content.ContextCompat;

public class WebViewController extends WebViewClient{
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        /*if (url.endsWith(".pdf")) {

            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            // if want to download pdf manually create AsyncTask here
            // and download file
            return true;
        } else{*/
            view.loadUrl(url);
            return true;
    //}
    }
}
