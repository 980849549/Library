package com.sjcdjsq.libs.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sjcdjsq.libs.net.RxApp;


/**
 * Created by Administrator on 2016/6/15.
 */
public class ToastUtils {

    public static void showToast(Context context, String content) {
        Toast toast = null;
        try {
            if (toast == null) {
                toast = toast.makeText(RxApp.application, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();
        } catch (Exception e) {
            Log.d(ToastUtils.class.getSimpleName(), "context is null");
        }
    }
}
