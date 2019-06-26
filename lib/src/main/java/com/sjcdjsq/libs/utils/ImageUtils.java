package com.sjcdjsq.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.lib.cooby.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    public final static String COOBY_SAVEPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public final static String FILE_SAVEPATH = COOBY_SAVEPATH + "/Cooby_News/Portrait/";
    public final static String COOBY_SAVEPATH_SNSFAIL = COOBY_SAVEPATH + "/Cooby_News/snsfail/";

    /**
     * 初始化本地图片
     */
    public static String getImagePath(Activity context) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(ImageUtils.FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            return null;
        }
        String path = null;
        try {
            path = ImageUtils.FILE_SAVEPATH + "ic_launcher_news.png";
            Bitmap pic = BitmapFactory.decodeResource(context.getResources(), R.mipmap.app_logo);
            saveImagePngToSD(context, path, pic, 100);
            File file = new File(path);
            if (file.exists()) {
                return path;
            }

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception t) {
            t.printStackTrace();
        }
        return path;
    }

    /**
     * 保存朋友圈发送失败图片
     */
    public static String getSnSImage(Activity context, String imageurl) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(ImageUtils.COOBY_SAVEPATH_SNSFAIL);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            return null;
        }
        String path = null;
        try {
            path = ImageUtils.COOBY_SAVEPATH_SNSFAIL + imageurl;
            Bitmap pic = BitmapFactory.decodeFile(imageurl);
            saveImagePngToSD(context, path, pic, 100);
            File file = new File(path);
            if (file.exists()) {
                return path;
            }

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception t) {
            t.printStackTrace();
        }
        return path;
    }


    public static void saveImagePngToSD(Context ctx, String filePath,
                                        Bitmap bitmap, int quality) throws IOException {
        if (bitmap != null) {
            File file = new File(filePath.substring(0,
                    filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(filePath));
            bitmap.compress(CompressFormat.PNG, quality, bos);
            bos.flush();
            bos.close();
        }
    }


}
