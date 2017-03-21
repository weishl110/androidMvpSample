package com.wei.androidmvpsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        View view = null;
        android.util.Log.e(TAG, "onCreateView: name = " + name);

        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        android.util.Log.e(TAG, "onCreateView: 22222 name = " + name);
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        new OkhttpRequest().url();
    }
}