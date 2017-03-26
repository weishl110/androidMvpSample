package com.wei.androidmvpsample.bean;

/**
 * Created by ${wei} on 2017/3/21.
 */

public class ResponseResult<T> {

    private String errorInfo;
    private String code;
    private String status;
    private T data;

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getCode() {
        return status;
    }

    public void setCode(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
