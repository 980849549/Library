package com.sjcdjsq.libs.net.basehttp;


import android.content.Context;

import com.lib.cooby.BuildConfig;
import com.sjcdjsq.libs.base.ui.RxCyActivity;
import com.sjcdjsq.libs.base.ui.RxCyFragment;
import com.sjcdjsq.libs.net.interceptors.CacheInterceptor;
import com.sjcdjsq.libs.net.subscribes.HttpSubscriber;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lib.cooby.R;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Administrator on 2016/5/9.
 */
public class HttpManager {
    private int[] certificates = {R.raw.cooby};
//    String hosts[] = {"https://192.168.0.128:7777"};

    private static class LazyHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    private HttpManager() {

    }

    public static final HttpManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * set HostnameVerifier
     * {@link HostnameVerifier}
     */
    protected HostnameVerifier getHostnameVerifier(final String[] hostUrls) {

        HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {

            public boolean verify(String hostname, SSLSession session) {
                boolean ret = false;
                for (String host : hostUrls) {
                    if (host.equalsIgnoreCase(hostname)) {
                        ret = true;
                    }
                }
                return ret;
            }
        };

        return TRUSTED_VERIFIER;
    }

    public void postAction(BaseApi baseApi) {
        if (baseApi == null)
            return;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(new CommonInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

//        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(RxApp.application, certificates);
//        if (sslSocketFactory != null) {
//            builder.sslSocketFactory(sslSocketFactory);
//        }
        if (baseApi.isCache()) {
            builder.addInterceptor(new CacheInterceptor(baseApi.isCache(), baseApi.getMethod()));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseApi.getBaseUrl())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        if (baseApi.getHttpSubscriber() != null)
            baseApi.getHttpSubscriber().disposeSb();
        HttpSubscriber subscriber = new HttpSubscriber(baseApi);
        Object o = baseApi.getRxCyActivity();
        Flowable flowable = baseApi.getFlowable(retrofit)
                .map(baseApi)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        if (o != null) {
            if (o instanceof RxCyActivity) {
                RxCyActivity rxCyActivity = (RxCyActivity) o;
                flowable.compose(rxCyActivity.bindUntilEvent(ActivityEvent.DESTROY));
            } else if (o instanceof RxCyFragment) {
                RxCyFragment rxCyFragment = (RxCyFragment) o;
                flowable.compose(rxCyFragment.bindUntilEvent(FragmentEvent.DESTROY));
            }
        }

        flowable.subscribe(subscriber);
    }

    protected SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            return null;
        }

    }
}
