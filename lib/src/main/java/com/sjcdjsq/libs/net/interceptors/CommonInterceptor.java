package com.sjcdjsq.libs.net.interceptors;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.sjcdjsq.libs.net.RxApp;
import com.sjcdjsq.libs.utils.AndroidDes3Util;
import com.sjcdjsq.libs.utils.AndroidUtils;
import com.sjcdjsq.libs.utils.LanguageUtil;

import java.io.IOException;
import java.util.UUID;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 公用参数
 */
public class CommonInterceptor implements Interceptor {
    private String avlVersions;
    private String avlSystemName = "0";
    private String avlMachineName = android.os.Build.MODEL;
    private String avlMachineId = "";

    public CommonInterceptor() {
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        avlVersions = AndroidUtils.getVersionCode(RxApp.application) + "";
        // TODO: 2017/2/24 0024 权限要添加
        try {
            TelephonyManager tm = (TelephonyManager) RxApp.application.getSystemService(Context.TELEPHONY_SERVICE);
            avlMachineId = tm.getDeviceId();
            avlMachineId = deal0(avlMachineId);
        } catch (Exception e) {
            String androidId = Settings.Secure.getString(RxApp.application.getContentResolver(), Settings.Secure.ANDROID_ID);
            avlMachineId = AndroidUtils.getLocalMacAddressFromWifiInfo(RxApp.application) + androidId;
        }

        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("avlVersions", avlVersions)
                .addQueryParameter("avlSystemName", avlSystemName)
                .addQueryParameter("avlMachineName", avlMachineName)
                .addQueryParameter("encodeMachineId", AndroidDes3Util.encode(avlMachineId))
                .addQueryParameter("avlMachineId", avlMachineId)
                .addQueryParameter("avlLanguageType", LanguageUtil.avlLanguageType)
                .addQueryParameter("avlMachineType", "android");

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);
    }

    /**
     * 处理部分手机设备号是00000000000000
     */
    public static String deal0(String machineId) {
        try {
            int avlMachineIdInt = Integer.parseInt(machineId);
            if (avlMachineIdInt == 0) {
                return getUniquePsuedoID();
            }
        } catch (Exception e) {
            return machineId;
        }
        return machineId;
    }

    /**
     * 返回 唯一的虚拟 ID
     *
     * @return ID
     */
    public static String getUniquePsuedoID() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        // API >= 9 的设备才有 android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // 如果用户更新了系统或 root 了他们的设备，该 API 将会产生重复记录
        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial";
        }

        // 最后，组合上述值并生成 UUID 作为唯一 ID
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}