package com.sjcdjsq.libs.utils.images;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;


/**
 * Created by Administrator on 2016/7/16.
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        int diskCacheSize = 1024 * 1024 * 250;//最多可以缓存多少字节的数据
        //设置磁盘缓存大小
//        builder.setDiskCache(new DiskLruCacheFactory(Constants.APP_CACHA_PATH, Constants.APP_CAHCA_NAME, diskCacheSize));
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
