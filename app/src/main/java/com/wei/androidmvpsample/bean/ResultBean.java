package com.wei.androidmvpsample.bean;

/**
 * Created by ${wei} on 2017/3/21.
 */

public class ResultBean<T> {

    private String errorInfo;
    private int code;
    private T date;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
