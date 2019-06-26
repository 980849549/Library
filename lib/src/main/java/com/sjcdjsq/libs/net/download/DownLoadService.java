package com.sjcdjsq.libs.net.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Karry on 2017/3/17 0017.
 */

public interface DownLoadService {
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
