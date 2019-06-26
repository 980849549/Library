package com.sjcdjsq.libs.net.basehttp;

/**
 * Created by Karry on 2017/2/15 0015.
 */

public class BaseResultBean<T> {
    private T data;
    private int code;
    private String errcode;
    private String msg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
