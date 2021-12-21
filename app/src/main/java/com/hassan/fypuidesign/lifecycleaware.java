package com.hassan.fypuidesign;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class lifecycleaware implements LifecycleObserver {
    private String TAG=this.getClass().getSimpleName();
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        Log.i(TAG, "onCreate: observer");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(){
        Log.i(TAG, "onPause: observer");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(){
        Log.i(TAG, "onResume: observer");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(){
        Log.i(TAG, "onstart: observer");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(){
        Log.i(TAG, "onStop: observer");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(){
        Log.i(TAG, "onDestroy: observer");
    }
}
