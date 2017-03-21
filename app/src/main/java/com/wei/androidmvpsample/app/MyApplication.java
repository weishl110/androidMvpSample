package com.wei.androidmvpsample.app;

import android.app.Application;

import com.wei.androidmvpsample.GloBalContext;

/**
 * Created by ${wei} on 2017/3/21.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GloBalContext.context = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(new MyHandler());
    }


    public class MyHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e) {

        }
    }


}
