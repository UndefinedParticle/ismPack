package com.example.ismpack.ui.Blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ismpack.R;
import com.example.ismpack.WebViewController;
import com.example.ismpack.databinding.FragmentBlogBinding;


public class BlogFragment extends Fragment {
    private WebView web2;
    private FragmentBlogBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BlogViewModel blogViewModel =
                new ViewModelProvider(this).get(BlogViewModel.class);

        binding = FragmentBlogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        web2=root.findViewById(R.id.BlogWebView);
        WebSettings webSettings2=web2.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        web2.loadUrl("https://twitter.com/IITISM_DHANBAD?s=20&t=vF3C8UhAvkzzbqOlazqpjw");
        if(web2.getUrl().endsWith(".pdf")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web2.getUrl())));
        } else{
            web2.setWebViewClient(new WebViewController());
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /*public void onBackPressed() {
        if(web1!= null && web1.canGoBack())
            web1.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }*/
}