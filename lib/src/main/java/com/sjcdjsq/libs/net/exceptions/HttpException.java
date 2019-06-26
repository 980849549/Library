package com.sjcdjsq.libs.net.exceptions;

import android.text.TextUtils;

/**
 * Created by Karry on 2017/2/16 0016.
 */

public class HttpException extends RuntimeException {
    private static final int DATA_FAIL = -1;
    private static final int SERVER_ERROR = -2;
    private static final int ACCOUNT_ERROR = -3;
    private static final int SESSION_TIMEOUT = -401;
    private static final int SESSION_KICK = -402;
    private static final int SESSION_PWD_CHANGE = -403;
    private static final int HIDE_TOAST = 0;

    public HttpException(int resultCode, String msg) {
        this(getExceptionMessage(resultCode, msg));
    }

    public HttpException(String detailMessage) {
        super(detailMessage);
    }

    private static String getExceptionMessage(int code, String msg) {
        if (!TextUtils.isEmpty(msg) && code != HIDE_TOAST)
            return "code=" + msg;
        switch (code) {
            case DATA_FAIL:
                msg = "code=获取数据失败";
                break;
            case SERVER_ERROR:
                msg = "code=服务器异常";
                break;
            case ACCOUNT_ERROR:
                msg = "code=账号或口令错误";
                break;
            case SESSION_TIMEOUT:
                msg = "code=SESSION过期";
                break;
            case SESSION_KICK:
                msg = "code=SESSION被人踢掉";
                break;
            case SESSION_PWD_CHANGE:
                msg = "code=ESSION密码被修改";
                break;
            case HIDE_TOAST:
                return "";
            default:
                msg = "code:" + code + "  error";
                break;
        }
        return "code=" + msg;
    }
}
