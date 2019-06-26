package com.sjcdjsq.libs.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lib.cooby.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseUIHelper {
    public final static int LISTVIEW_ACTION_INIT = 0x01;
    public final static int LISTVIEW_ACTION_REFRESH = 0x02;
    public final static int LISTVIEW_ACTION_SCROLL = 0x03;

    public final static int LISTVIEW_DATA_MORE = 0x01;
    public final static int LISTVIEW_DATA_LOADING = 0x02;
    public final static int LISTVIEW_DATA_FULL = 0x03;
    public final static int LISTVIEW_DATA_EMPTY = 0x04;

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void ToastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void LongToastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_LONG).show();
    }


    public static void showSaveImgDialog(final Activity ac, final String url) {
        new AlertDialog.Builder(ac).setTitle("图片").setMessage(R.string.store_to_phone).setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int i) {
                        Glide.with(ac).load(url).asBitmap().listener(new RequestListener<String, Bitmap>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                LongToastMessage(ac, e.toString());

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                try {
                                    File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/DCIM/camera");
                                    if (!appDir.exists()) {
                                        appDir.mkdir();
                                    }
                                    String fileName = System.currentTimeMillis() + ".jpg";
                                    File file = new File(appDir, fileName);
                                    try {
                                        FileOutputStream fos = new FileOutputStream(file);
                                        resource.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                        fos.flush();
                                        fos.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    ac.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                                            + file.getAbsolutePath())));
//									String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//									String path=MediaStore.Images.Media.insertImage(ac.getContentResolver(), loadedImage, timeStamp, timeStamp);
                                    LongToastMessage(ac, "图片保存至：" + file.getAbsolutePath());
                                } catch (Exception e) {
                                    LongToastMessage(ac, "异常");
                                } catch (OutOfMemoryError e) {
                                    LongToastMessage(ac, "内存溢出");
                                }
                                return false;
                            }
                        });
                    }
                }).setNegativeButton(R.string.not, null)
                .show();
    }
}
