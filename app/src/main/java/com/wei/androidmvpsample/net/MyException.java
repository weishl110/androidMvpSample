package com.wei.androidmvpsample.net;

/**
 * Created by ${wei} on 2017/3/21.
 */

public class MyException extends Exception {

    private String message;
    private int code;

    public MyException() {
    }

    public MyException(String message) {
        super(message);
        this.message = message;
    }

    public MyException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
