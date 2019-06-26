package com.sjcdjsq.libs.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lib.cooby.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zdd
 * @Description:检查工具类
 * @date 2014年11月18日 下午4:47:48
 * @remark
 */
public class CheckUtil {


    /**
     * @param ctx
     * @param canEmpty 邮箱是否可以为空
     * @return boolean false--格式错误;true--格式正确
     * @Description:判断邮箱格式，只能使用 ==> 字母 数字 . @ _
     */
    public static boolean checkEmail(Context ctx, String emailString,
                                     boolean canEmpty) {
        Pattern p = Pattern
                .compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        if (TextUtils.isEmpty(emailString)) {
            if (canEmpty) {
                return true;
            } else {
                Toast.makeText(ctx, R.string.email_cannot_empty,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Matcher matcher = p.matcher(emailString);
        if (!matcher.matches()) {
            Toast.makeText(ctx, R.string.email_format_error, Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    public static String formatURL(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!url.toLowerCase().startsWith("http://")) {
                return "http://" + url;
            } else if (!url.toLowerCase().startsWith("https://")) {
                return "https://" + url;
            }
        }
        return url;
    }

}
