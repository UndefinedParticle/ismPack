package com.example.ismpack.ui.Feed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ismpack.R;
import com.example.ismpack.WebViewController;
import com.example.ismpack.databinding.FragmentFeedBinding;


public class FeedFragment extends Fragment {
    private WebView web2;
    private FragmentFeedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FeedViewModel feedViewModel =
                new ViewModelProvider(this).get(FeedViewModel.class);

        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        web2=root.findViewById(R.id.FeedWebView);
        WebSettings webSettings2=web2.getSettings();
        webSettings2.setJavaScriptEnabled(true);
        web2.loadUrl("https://www.linkedin.com/school/iitism/posts/?feedView=all");
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