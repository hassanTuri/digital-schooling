package com.hassan.fypuidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new lifecycleaware());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {

        super.onPause();
    }
    @Override
    protected void onResume() {

        super.onResume();
    }
    @Override
    protected void onStart() {

        super.onStart();
    }
    @Override
    protected void onStop() {

        super.onStop();
    }
}