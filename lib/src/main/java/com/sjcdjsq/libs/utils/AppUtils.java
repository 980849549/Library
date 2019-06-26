package com.sjcdjsq.libs.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class AppUtils {
    public static boolean isGoogle(Context context) {
        String channelValue = AndroidUtils.getMetaData(context, "UMENG_CHANNEL");
        if (channelValue.equalsIgnoreCase("google")) {
            return true;
        }
        return false;
    }
    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
