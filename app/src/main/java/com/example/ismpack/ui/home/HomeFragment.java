package com.example.ismpack.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ismpack.R;
import com.example.ismpack.WebViewController;
import com.example.ismpack.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {
    private WebView web1;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        web1=root.findViewById(R.id.HomeWebView);
        WebSettings webSettings1=web1.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        //https://www.iitism.ac.in/iitismnew/
        web1.loadUrl("https://www.iitism.ac.in/");
        if(web1.getUrl().endsWith(".pdf")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web1.getUrl())));
        } else{
            web1.setWebViewClient(new WebViewController());
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    // In your Fragment
    @Override
    public void onResume() {
        super.onResume();
        // Enable back button callback
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Remove back button callback
        callback.remove();
    }

    // Define the onBackPressed callback
    private OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            // Handle back button press
            requireActivity().finishAffinity();
        }
    };



    /*public void onBackPressed() {
        if(web1!= null && web1.canGoBack())
            web1.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }*/
}