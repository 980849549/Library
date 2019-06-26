package com.sjcdjsq.libs.utils;

import com.sjcdjsq.libs.net.RxApp;
import com.lib.cooby.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaseStringUtils {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * yyyy-MM-dd
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * MM-dd HH:mm
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater5 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
    };
    /**
     * HH:mm
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };


    /**
     * MM-dd
     */
    private final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("ddMM月");
        }
    };

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPass(String pass) {
        if (BaseStringUtils.isEmpty(pass)) {
            return false;
        }
        if (pass.trim().length() < 6 || pass.trim().length() > 20) {
            return false;
        }
        return true;
    }

    public static String valueOf(String input) {
        return input == null ? "" : input;
    }

    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    public static String getTodayFormater() {
        try {
            return dateFormater.get().format(new Date());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 显示24小时内的时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = null;
        time = toDate(sdate);
        if (time == null) {
            return "";
        }
        String ftime = "";
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 判断是否是同一天
            String curDate = sFormat.format(cal.getTime());//获取当前时间
            String paramDate = sFormat.format(sFormat.parse(sdate));//接收获取到的时间
            if (isToday(sdate)) {//同一天
                ftime = dealTime(time, ftime, cal, curDate, paramDate);
                return ftime;
            } else {//不同天  4-25:11:11
                ftime = dateFormater5.get().format(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftime;
    }

    /**
     * 显示7天内时间
     *
     * @param sdate
     * @return
     */
    public static String day7_time(String sdate) {
        Date time = null;
        time = toDate(sdate);
        if (time == null) {
            return "";
        }
        String ftime = "";
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 判断是否是同一天
            String curDate = sFormat.format(cal.getTime());//获取当前时间
            String paramDate = sFormat.format(sFormat.parse(sdate));//接收获取到的时间
            if (isToday(sdate)) {//同一天
                ftime = dealTime(time, ftime, cal, curDate, paramDate);
                return ftime;
            } else {//不同天  4-25:11:11
                ftime = dealTime7(time, ftime, cal, curDate, paramDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftime;
    }

    public static String friendly_time_2(String sdate) {
        Date time = null;
//		if (TimeZoneUtil.isInEasternEightZones()) {
        time = toDate(sdate);
//		} else {
//			time = TimeZoneUtil.transformTime(toDate(sdate),
//					TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
//		}
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 判断是否是同一天
            String curDate = sFormat.format(cal.getTime());//获取当前时间
            String paramDate = sFormat.format(sFormat.parse(sdate));//接收获取到的时间
            if (curDate.equals(paramDate)) {
                ftime = "今天";
                return ftime;
            }

            long lt = toDate2(paramDate).getTime();
            long ct = toDate2(curDate).getTime();
            long d = 1000 * 60 * 60 * 24;
            long days = (ct - lt) / d;
            if (days == 0) {
                int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
                if (hour == 0)
                    ftime = Math.max(
                            (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                            + "分钟前";
                else
                    ftime = hour + "小时前";
            } else if (days == 1) {
                ftime = "昨天";
            } else if (days > 1) {
                ftime = dateFormater4.get().format(time);
            } else if (days < 0) {
                ftime = dateFormater4.get().format(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftime;
    }

    public static String friendly_time_3(String sdate) {
        Date time = null;
        time = toDate(sdate);
        if (time == null) {
            return "";
        }

        String ftime = "";

        ftime = dateFormater2.get().format(time);
        return ftime;
    }

    private static String dealTime(Date time, String ftime, Calendar cal, String curDate, String paramDate) {
        long lt = toDate2(paramDate).getTime();
        long ct = toDate2(curDate).getTime();
        long d = 1000 * 60 * 60 * 24;
        long days = (ct - lt) / d;

        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + RxApp.application.getString(R.string.mins_ago);
            } else if (hour > 0) {

                ftime = dateFormater3.get().format(time);
            }
        } else if (days > 0) {
            ftime = dateFormater2.get().format(time);
        } else if (days < 0) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    private static String dealTime7(Date time, String ftime, Calendar cal, String curDate, String paramDate) {
        long lt = toDate2(paramDate).getTime();
        long ct = toDate2(curDate).getTime();
        long d = 1000 * 60 * 60 * 24;
        long days = (ct - lt) / d;
        ftime = "";
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + RxApp.application.getString(R.string.mins_ago);
            } else if (hour > 0) {
                ftime = dateFormater3.get().format(time);
            }
        } else if (days > 0 && days < 7) {
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * yyyy-MM-dd
     *
     * @param sdate
     * @return
     */
    public static String simple_time(String sdate) {
        Date time = null;
        time = toDate(sdate);
        if (time == null) {
            return "";
        }

        String ftime = "";

        ftime = dateFormater2.get().format(time);
        return ftime;
    }


    /**
     * 开始时间晚于当前时间 就是未开始 结束时间早于当前时间就是已过期
     *
     * @param sdate
     * @return
     */
    public static int compareToTime(String sdate) {
        // 判断是否是同一天
        try {
            Calendar curDate = Calendar.getInstance();
            curDate.setTime(new Date());
            java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c1 = Calendar.getInstance();
            c1.setTime(df.parse(sdate));
            int result = c1.compareTo(curDate);
            if (result == 0) {
                System.out.println("c1相等curDate");
                return 0;
            } else if (result < 0) {
                //结束时间 -已过期
                System.out.println("c1小于curDate");
                return 1;
            } else {
                //开始时间-未开始
                System.out.println("c1大于curDate");
                return 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date toDate2(String sdate) {
        try {
            return dateFormater2.get().parse(sdate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    public static String dateByFormat(long time, String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(time));
    }


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
     * 是否在24小内
     *
     * @param sdate
     * @return
     */
    public static boolean isSevenDay(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            long diff = today.getTime() - time.getTime();
            long i = 24 * 60 * 60 * 1000 * 7;
            if (i >= diff) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 是否超时
     *
     * @param sdate
     * @param timeLimit 时限
     * @return
     */
    public static boolean isTimeout(String sdate, long timeLimit) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            long diff = today.getTime() - time.getTime();
            long i = timeLimit * 60 * 1000;
            if (i >= diff) {
                b = true;
            }
        }
        return b;
    }
}
