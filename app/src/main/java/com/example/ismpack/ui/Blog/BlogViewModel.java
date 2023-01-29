package com.example.ismpack.ui.Blog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BlogViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public BlogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Blog fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}