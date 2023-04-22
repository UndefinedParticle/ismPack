package com.example.ismpack.ui.NotceBoard;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ismpack.R;
import com.example.ismpack.WebViewController;
import com.example.ismpack.databinding.FragmentNoticeBinding;


public class NoticeFragment extends Fragment {

    FragmentNoticeBinding binding;
    WebView webView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        webView = view.findViewById(R.id.NoticeWebView);

        WebSettings webSettings1=webView.getSettings();
        webSettings1.setJavaScriptEnabled(true);

        webSettings1.setBuiltInZoomControls(true);
        webSettings1.setDisplayZoomControls(false);
        webSettings1.setSupportZoom(true);
        webSettings1.setUseWideViewPort(true);
        webSettings1.setLoadWithOverviewMode(true);
        webView.setInitialScale(1);

        webView.loadUrl("https://www.iitism.ac.in/announcement");
        if(webView.getUrl().endsWith(".pdf")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl())));
        } else{
            webView.setWebViewClient(new WebViewController());
        }

        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                if (mimetype.equals("application/pdf")) {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setMimeType(mimetype);
                    String cookies = CookieManager.getInstance().getCookie(url);
                    request.addRequestHeader("cookie", cookies);
                    request.addRequestHeader("User-Agent", userAgent);
                    request.setDescription("Downloading file...");
                    request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));
                    DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Toast.makeText(getContext().getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}