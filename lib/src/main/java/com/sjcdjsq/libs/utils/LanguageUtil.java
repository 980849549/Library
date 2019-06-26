package com.sjcdjsq.libs.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by turbo on 2017/2/16.
 */

public class LanguageUtil {
    public static String avlLanguageType;
    public static void changeLanguageType(Context context, Locale localelanguage) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        // 参考 https://developer.android.com/reference/android/content/res/Configuration.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(localelanguage);
        } else {
            config.locale = localelanguage;
        }
        resources.updateConfiguration(config, dm);
    }

    public static Locale getLanguageType(Context context) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return config.getLocales().get(0);
        } else {
            return config.locale;
        }
    }

}
