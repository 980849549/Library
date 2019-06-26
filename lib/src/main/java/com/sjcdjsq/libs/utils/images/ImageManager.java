package com.sjcdjsq.libs.utils.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.lib.cooby.R;

import java.io.File;


public class ImageManager {
    public static String mURL = "";
    private Context mContext;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public ImageManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    // 加载网络图片
    public void loadUrlImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(R.drawable.newloading)
                .error(R.drawable.newloading)
                .dontAnimate()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    // 加载网络图片
    public void loadlocalImageNoImageTye(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.banner_loading)
                .dontAnimate()
                .error(R.drawable.banner_loading)
                .into(imageView);
    }

    // 加载网络背景图片
    public void loadBgImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .dontAnimate()
                .error(R.drawable.ic_user_bg)
                .into(imageView);
    }

    // 加载gif图片
    public void loadGifImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    // 加载网络图片
    public void loadUrlImage(String url, ImageView imageView, int draw) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(draw)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    // 加载drawable图片
    public void loadResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    // 加载drawable图片
    public void loadResImage(int resId, ImageView imageView, int draw) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(draw)
                .dontAnimate()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    // 加载本地图片
    public void loadRoundLocalImage(String path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        File file = new File(path);
        Glide.with(mContext)
                .load(file)
                .centerCrop()
                .dontAnimate()
                .transform(new GlideRoundTransform(mContext))
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(String path, ImageView imageView, int draw) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(draw)
                .dontAnimate()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(R.drawable.default_avatar)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(String url, int defaultImg, ImageView imageView) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(defaultImg)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public void loadCircleResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .dontAnimate()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载本地圆型图片
    public void loadCircleLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .dontAnimate()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }


    public void loadBlurImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(R.drawable.newloading)
                .error(R.drawable.newloading)
                .dontAnimate()
                .bitmapTransform(new BlurTransformation(mContext)) // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
                .into(imageView);
    }

    public void loadRoundImage(String url, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(R.drawable.banner_loading)
                .error(R.drawable.banner_loading)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideRoundTransform(mContext))
                .into(imageView);
    }

    public void loadRoundImage(String url, ImageView imageView, int draw) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(draw)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideRoundTransform(mContext, 8))
                .into(imageView);
    }

    public void loadRoundImage(String url, ImageView imageView, int draw, int degress) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(isAbsolutely(url))
                .placeholder(draw)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideRoundTransform(mContext, degress))
                .into(imageView);
    }

    public void loadNotypeImage(String path, final ImageView imageView, final OnImageLoadCallBack onImageLoadCallBack) {
        Glide.with(mContext).load(path).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                onImageLoadCallBack.onFail();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                onImageLoadCallBack.onSuccess();
                return false;
            }
        }).into(new ImageViewTarget<GlideDrawable>(imageView) {
            @Override
            protected void setResource(GlideDrawable resource) {
                imageView.setImageDrawable(resource);
                onImageLoadCallBack.onReadyBitmap(drawableToBitmap(resource));
            }
        });
    }

    public void getUrlBitmap(String url, final OnLoadBitmpCallBack onLoadBitmpCallBack) {
//        Bitmap myBitmap = Glide.with(applicationContext)
//                .load(yourUrl)
//                .asBitmap() //必须
//                .centerCrop()
//                .into(500, 500)
//                .get();
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                onLoadBitmpCallBack.onReadyBitmap(resource);
            }
        });
    }

    public String isAbsolutely(String url) {
        if (TextUtils.isEmpty(url))
            return url;
        if (url.contains("http://") || url.contains("https://"))
            return url;
        else
            return mURL + url;
    }

    public interface OnImageLoadCallBack {
        void onSuccess();

        void onReadyBitmap(Bitmap bitmap);

        void onFail();
    }

    public interface OnLoadBitmpCallBack {
        void onReadyBitmap(Bitmap bitmap);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }
}
