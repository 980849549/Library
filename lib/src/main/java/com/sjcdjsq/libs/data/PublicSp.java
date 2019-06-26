package com.sjcdjsq.libs.data;

import com.sjcdjsq.libs.net.RxApp;
import com.sjcdjsq.libs.utils.PreferenceHelper;

/**
 * Created by Karry on 2017/3/8 0008.
 */

public class PublicSp {
    private final static String PUBLIC_SP = "public_sp";

    private final static String APPNAME = "app_name";
    private final static String CHANNELNAME = "chanel_name";
    private final static String BRANDNAME = "brand_name";
    private final static String ISIOPENGAME = "is_open_game";
    private final static String ISIOPENAD = "is_open_ad";

    private final static String AD_LEFT_ON = "ad_left_on";
    private final static String AD_CENTER_ON = "ad_center_on";
    private final static String AD_TABLE_ON = "ad_table_on";


    public static void setAppName(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, APPNAME, s);
    }

    public static String getAppName() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, APPNAME);
    }

    public static void setChannelName(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, CHANNELNAME, s);
    }

    public static String getChannelName() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, CHANNELNAME);
    }

    public static void setBrandName(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, BRANDNAME, s);
    }

    public static String getBrandName() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, BRANDNAME);
    }

    public static void setIsOpenGame(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, ISIOPENGAME, s);
    }


    public static void setad_left_on(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, AD_LEFT_ON, s);
    }

    public static String getad_left_on() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, AD_LEFT_ON);
    }

    public static void setad_center_on(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, AD_CENTER_ON, s);
    }

    public static String getad_center_on() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, AD_CENTER_ON);
    }

    public static void setad_table_on(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, AD_TABLE_ON, s);
    }

    public static String getad_table_on() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, AD_TABLE_ON);
    }

    public static String getIsOpenGame() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, ISIOPENGAME);
    }

    public static void setIsOpenAd(String s) {
        PreferenceHelper.write(RxApp.application, PUBLIC_SP, ISIOPENAD, s);
    }

    public static String getIsOpenAd() {
        return PreferenceHelper.readString(RxApp.application, PUBLIC_SP, ISIOPENAD);
    }


}
