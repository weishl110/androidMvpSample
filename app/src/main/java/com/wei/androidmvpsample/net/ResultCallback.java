package com.wei.androidmvpsample.net;

/**
 * Created by ${wei} on 2017/3/21.
 */

public abstract class ResultCallback<T> {

    public abstract void onError(Exception e);

    public abstract void response(T t);
}
