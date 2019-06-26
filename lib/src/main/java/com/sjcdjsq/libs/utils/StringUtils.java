package com.sjcdjsq.libs.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.Date;


public class StringUtils extends BaseStringUtils {
    /**
     * 是否在24小内
     *
     * @param sdate
     * @return
     */
    public static boolean isTwentyFour(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            long diff = today.getTime() - time.getTime();
            long i = 24 * 60 * 60 * 1000;
            if (i >= diff) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 字符串部分颜色设置
     *
     * @param context
     * @param str
     * @param color
     * @param s
     * @return
     */
    public static SpannableStringBuilder textInColor(Context context, String str, int color, String... s) {
        SpannableStringBuilder t = new SpannableStringBuilder(str);
        for (int i = 0; i < s.length; i++) {
            if (TextUtils.isEmpty(str))
                str = "";
            int start = str.lastIndexOf(s[i]);
            int bend = start + s[i].length();
            if (start == -1)
                continue;
            t.setSpan(new ForegroundColorSpan(getColor(context, color)), start, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return t;
    }


    public static final int getColor(Context context, int id) {
        if (context == null)
            return 0;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context.getApplicationContext(), id);
        } else {
            return context.getResources().getColor(id);
        }
    }

}
