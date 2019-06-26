package com.sjcdjsq.libs.net.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 缓存拦截器
 * Created by Karry on 2017/2/17 0017.
 */

public class CacheInterceptor implements Interceptor {
    private boolean mIsCache;
    private String mMethon;

    public CacheInterceptor(boolean isCache, String methon) {
        mIsCache = isCache;
        mMethon = methon;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Response response = chain.proceed(request);
//        if (mIsCache) {
//            ResponseBody body = response.body();
//            BufferedSource source = body.source();
//            source.request(Long.MAX_VALUE); // Buffer the entire body.
//            Buffer buffer = source.buffer();
//            Charset charset = Charset.defaultCharset();
//            MediaType contentType = body.contentType();
//            if (contentType != null) {
//                charset = contentType.charset(charset);
//            }
//            String bodyString = buffer.clone().readString(charset);
//            CacheEntity cacheEntity = new CacheEntity();
//            cacheEntity.setCacheTime(System.currentTimeMillis());
//            cacheEntity.setCacheResult(bodyString);
//            cacheEntity.setMethon(mMethon);
////            Log.d(getClass().getSimpleName(), "cache name:"+mMethon);
//            DBManager.getInstance(RxApp.application).saveCacheData(cacheEntity);
//        }
        return null;
    }
}
