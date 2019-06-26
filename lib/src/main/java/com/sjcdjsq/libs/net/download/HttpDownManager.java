package com.sjcdjsq.libs.net.download;


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.sjcdjsq.libs.net.RxApp;
import com.sjcdjsq.libs.utils.FileUtils;
import com.sjcdjsq.libs.utils.ToastUtils;
import com.sjcdjsq.libs.utils.ZipUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * david
 */
public class HttpDownManager {
    private String path;

    private static class LazyHolder {
        private static final HttpDownManager INSTANCE = new HttpDownManager();
    }

    private HttpDownManager() {

    }

    public static final HttpDownManager getInstance() {
        return HttpDownManager.LazyHolder.INSTANCE;
    }

    private Retrofit initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.188:8888/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        return retrofit;
    }

    /**
     * @param url
     */
    public void startDownImg(final String url) {
        Retrofit retrofit = initRetrofit();
        Call<ResponseBody> call = retrofit.create(DownLoadService.class).downloadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String fileName = FileUtils.getFileName(url);
                boolean b = writeResponseBodyToDisk(response.body(), fileName);
                if (b) {
                    ToastUtils.showToast(RxApp.application, "保存完成 目录cooby_picture");

                } else {
                    ToastUtils.showToast(RxApp.application, "保存失败");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ToastUtils.showToast(RxApp.application, "保存失败");
            }
        });

    }

    /**
     * @param url
     */
    public void startDownAdZip(final String url, final String path, final String unZipPath) {
        Retrofit retrofit = initRetrofit();
        Call<ResponseBody> call = retrofit.create(DownLoadService.class).downloadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                boolean b = writeResponseBodyToDisk(response.body(), FileUtils.getFileName(url));
                if (b) {
                    ZipUtils.unZip(path, unZipPath);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
        try {
            if (TextUtils.isEmpty(path))
                path = Environment.getExternalStorageDirectory() + "/cooby_picture";
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            File file = new File(f, fileName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

//                    fileSizeDownloaded += read;
                }

                outputStream.flush();
                return true;
            } catch (Exception e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
                //通知相册
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                RxApp.application.sendBroadcast(intent);
            }
        } catch (Exception e) {
            return false;
        }

    }


    public void setPath(String path) {
        this.path = path;
    }
}
