package com.sjcdjsq.libs;

import android.text.TextUtils;

public class BaseURLs {
    public final static String HTTP = "http://";
    public static String HOST = "192.168.0.188:8888";
    /**
     * 提交bug日志
     */
    public final static String appuploadlog = "/appuploadlog/appuploadlog_uploadAndroidLog.do";

    /**
     * 图片，网页地址
     *
     * @param
     * @return
     */
    public static String getURL() {
        StringBuilder url = new StringBuilder();
        url.append(HTTP).append(HOST).append("/");
        return url.toString();

    }

    /**
     * 检查路劲是否需要进行拼接
     *
     * @param url
     * @return
     */
    public static String formatURL(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!url.toLowerCase().startsWith("http")) {
                url = getURL() + url;
            }
        }
        return url;
    }

    public static String getURLMethod() {
        StringBuilder url = new StringBuilder();
        url.append(HTTP).append(HOST).append("/mobile");
        return url.toString();
    }

    public static String getURLMethod(String method) {
        StringBuilder url = new StringBuilder();
        url.append(HTTP).append(HOST).append("/mobile").append(method);
        return url.toString();
    }

    public static String getServiceURL(String method) {
        StringBuilder url = new StringBuilder();
        url.append(HTTP).append(HOST).append("/").append(method);
        return url.toString();
    }
}
