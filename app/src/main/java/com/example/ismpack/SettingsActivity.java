package com.example.ismpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");

    }
}