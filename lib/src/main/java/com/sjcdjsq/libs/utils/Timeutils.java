package com.sjcdjsq.libs.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class Timeutils {

    /**
     * 获取时区
     *
     * @return
     */
    public static String getTimezone() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        double zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        double timezone = zoneOffset / 60 / 60 / 1000;// 时区，东时区数字为正，西时区为负
        return timezone + "";
    }

    /**
     * 指定格式返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 超过7天
     * @param time
     * @return
     */
    public static boolean surpassOneWeek(long time) {
        long t = System.currentTimeMillis();
        long last = t - time;
        if (last >= 7*24*60*60*1000){
            return true;
        }
        return false;
    }

    /**
     * 超过6小时
     * @param time
     * @return
     */
    public static boolean surpassSixHours(long time) {
        long t = System.currentTimeMillis();
        long last = t - time;
        if (last >= 6*60*60*1000){
            return true;
        }
        return false;
    }
}
