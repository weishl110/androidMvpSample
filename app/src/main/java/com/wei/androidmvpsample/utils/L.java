package com.wei.androidmvpsample.utils;

import android.util.Log;

import com.wei.androidmvpsample.BuildConfig;

/**
 * Created by ${wei} on 2017/3/20.
 */

public class L {

    private static boolean DEBUG = BuildConfig.LOG_DEBUG != 0;

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag,String msg){
        if(DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }
}
