package com.sjcdjsq.libs.net.subscribes;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.sjcdjsq.libs.net.RxApp;
import com.sjcdjsq.libs.net.basehttp.BaseApi;
import com.sjcdjsq.libs.net.listeners.OnHttpListener;
import com.sjcdjsq.libs.widgets.LoadingDialog;
import com.lib.cooby.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Karry on 2017/2/15 0015.
 */

public class HttpSubscriber<T> extends ResourceSubscriber<T> {
    private BaseApi mBaseApi;
    private OnHttpListener mOnHttpListener;
    private boolean isShowDialog;
    private LoadingDialog mLoadingDialog;

    public HttpSubscriber(BaseApi baseApi) {
        this.mBaseApi = baseApi;
        this.mOnHttpListener = baseApi.getOnHttpListener();
        baseApi.setHttpSubscriber(this);
        if (baseApi.isShowProgress()) {
            isShowDialog = true;
            initProgress();
        }
    }

    private void initProgress() {
        Object o = mBaseApi.getRxCyActivity();
        mLoadingDialog = LoadingDialog.newInstance();
        mLoadingDialog.setOutside(mBaseApi.isOutSideTouch());
        if (o instanceof Activity) {
            mLoadingDialog.init((Activity) o);
        } else {
            mLoadingDialog.init(((Fragment) o).getActivity());
        }

//        mLoadingDialog.setCanceledOnTouchOutside(mBaseApi.isOutSideTouch());
//        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                disposeSb();
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDialog();
        mOnHttpListener.onStart();
    }


    @Override
    public void onNext(T t) {
        try {
            mOnHttpListener.onNext(t);
        } catch (Exception e) {
        }
        onComplete();
    }

    @Override
    public void onError(Throwable t) {
        errorDeal(t);
        onComplete();
    }

    @Override
    public void onComplete() {
        closeDialog();
        mOnHttpListener.onComplete();
    }

    /*错误统一处理*/
    private void errorDeal(Throwable e) {
        Context context = RxApp.application;
        if (context == null) return;
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, R.string.network_unusual, Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, R.string.network_interrupt, Toast.LENGTH_SHORT).show();
        } else {
            String errorMsg = e.getMessage();
            if (!TextUtils.isEmpty(errorMsg)) {
                if (errorMsg.contains("code=")) {
                    Toast.makeText(context, errorMsg.replace("code=", ""), Toast.LENGTH_SHORT).show();
                }
            }

        }
        if (mOnHttpListener != null) {
            mOnHttpListener.onError(e);
        }
    }

    public void disposeSb() {
        dispose();
        mOnHttpListener.onCancel();
    }

    private void showDialog() {
        if (!isShowDialog)
            return;
        if (mLoadingDialog != null)
            mLoadingDialog.showDialog();
    }

    private void closeDialog() {
        if (!isShowDialog)
            return;
        if (mLoadingDialog != null)
            mLoadingDialog.dismissDialog();
    }
}
