package com.sjcdjsq.libs.net.listeners;

/**
 * Created by Karry on 2017/2/15 0015.
 */

public abstract class OnHttpListener<T> {
    /**
     * 成功
     */
    public abstract void onNext(T value) throws Exception;

    /**
     * 开始
     */
    public void onStart() {
    }


    /**
     * 完成
     */
    public void onComplete() {
    }


    /**
     * 异常
     */
    public abstract void onError(Throwable e);


    /**
     * 取消
     */
    public void onCancel() {
    }


    /**
     * 缓存
     */
    public void onNextCache(String value) {
    }

}
