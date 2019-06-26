package com.sjcdjsq.libs.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    public static final String MEMBER_ONECE = "member_once";//一次
    public static final String MEMBER_ONE_DAY = "member_one_day";//一天
    public static final String MEMBER_A_WEEK = "member_a_week";//一周
    public static final String MEMBER_ONE_MONTH = "member_one_month";//一个月
    public static final String MEMBER_ONE_YEAR = "member_one_year";//一年
    public static final String MEMBER_FOREVER = "member_forever";//永久

    public static final int MONEY_ONCE = 2;//一次
    public static final int MONEY_ONE_DAY = 5;//一天
    public static final int MONEY_A_WEEK = 10;//一个月
    public static final int MONEY_ONE_MONTH = 30;//一个月
    public static final int MONEY_YEAR = 99;//一年

    public static final int ONE_DAY = 1;//一天
    public static final int SEVEN_DAY = 7;//七天
    public static final int ONE_MONTH = 30;//一个月
    public static final int ONE_YEAR = 365;//一个月

    /**
     * userIcon : http://q.qlogo.cn/qqapp/1105794744/7F4B82754E95451EF9F147B9ADD3B2FD/40
     * userName : 糅月露影
     * platformNname : QQ
     * token : 930A282EBC3E7EA9D2DB93E4D7CC2407
     * userId : 1073
     */

    private String userIcon;
    private String userName;
    private String platformNname;
    private String token;
    private String userId;
    private String secrets;

    public String getSecrets() {
        return secrets;
    }

    public void setSecrets(String secrets) {
        this.secrets = secrets;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlatformNname() {
        return platformNname;
    }

    public void setPlatformNname(String platformNname) {
        this.platformNname = platformNname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
