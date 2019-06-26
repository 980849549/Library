package com.sjcdjsq.libs.net;

import android.app.Application;

/**
 * Created by Karry on 2017/2/16 0016.
 */

public class RxApp {

    public static String BASE_URL;

    public static Application application;

    private static class LazyHolder {
        private static final RxApp INSTANCE = new RxApp();
    }

    private RxApp() {

    }

    public static final RxApp getInstance() {
        return RxApp.LazyHolder.INSTANCE;
    }

    public void init(Application application) {
        this.application = application;
    }

    public void setBaseUrl(String baseUrl) {
        this.BASE_URL = baseUrl;
    }
}
