# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5                          # 指定代码的压缩级别
-dontusemixedcaseclassnames                    # 是否使用大小写混合
-dontskipnonpubliclibraryclasses               # 是否混淆第三方jar
-dontpreverify                                 # 混淆时是否做预校验
-ignorewarnings
-verbose
-optimizations !code\simplification\arithmetic,!field\*,!class\merging\*       # 混淆时所采用的算法
# 避免混淆泛型
-keepattributes Singature

-keepattributes InnerClasses,EnclosingMethod
-keep class android.** {*; }
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {                 # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembernames class * {                 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembernames class * {                 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers enum * {                            # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {      # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keep class android.webkit.JavascriptInterface {*;}

-keep class android.support.v4.** { *; }
-keep class com.baidu.** { *; }
-keep class org.apache.http.entity.mime.** { *; }
-keep class com.nineoldandroids.** { *; }
-keep class org.apache.commons.httpclient.** { *; }
-keep class org.acra.** { *; }
-keep class org.apache.commons.lang3.** { *; }
-keep class com.alibaba.fastjson.** { *; }
-keep class com.amap.api.location.** { *; }
-keep class org.osmdroid.** { *; }
-keep class org.metalev.multitouch.controller.** { *; }
-keep class microsoft.mappoint.** { *; }
-keep class com.squareup.picasso.** { *; }
-keep class com.google.gson.** { *; }
-keep class de.greenrobot.dao.** { *; }
-keep class android.support.v7.app.** { *; }
-keep class org.mapsforge.android.** { *; }
-keep class org.mapsforge.core.** { *; }
-keep class org.mapsforge.graphics.android.** { *; }
-keep class org.mapsforge.map.** { *; }
-keep class com.amap.api.mapcore.** { *; }
-keep class com.amap.api.maps.** { *; }
-keep class com.autonavi.amap.mapcore.** { *; }

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
#-----------------------------
#分享sdk不需要混淆的-----------
-keep class m.framework.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
#--------------------------
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}

#友盟统计的jar包不要混淆
-keep class com.umeng.** { *; }
-dontwarn com.umeng.**

#警告
-dontwarn com.baidu.lightapp.**
-dontwarn com.squareup.picasso.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.commons.httpclient.**
-dontwarn com.baidu.android.pushservice.apiproxy.**
-dontwarn com.baidu.frontia.api.**
-dontwarn com.alibaba.fastjson.**
-dontwarn com.baidu.android.pushservice.**
-dontwarn com.baidu.frontia.**
-dontwarn org.slf4j.**

-keepnames class net.cooby.app.utils.images.MyGlideModule

-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**

#sharesdk
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn **.R$*

#短信SDK的混淆
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class cn.smssdk.**{*;}
-keep class com.mob.**{*;}
-keep class **.R$* {
    *;
}
#green
-keep class de.greenrobot.dao.** {*;}

-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class org.greenrobot.greendao.**{*;}
-dontwarn org.greenrobot.greendao.**

-keep class com.squareup.okhttp.**{*;}
-dontwarn com.squareup.okhttp.**

-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http:\\proguard.sourceforge.net\manual\examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http:\\proguard.sourceforge.net\manual\examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keep class android.support.v4.** {*;}

-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.squareup.picasso.* {*;}

-keep class com.hyphenate.* {*;}
-keep class com.hyphenate.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
#不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
-keep class com.hyphenate.chatuidemo.utils.SmileUtils {*;}

#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}

-keep class com.hyphenate.** {*;}
-keep class com.superrtc.** {*;}

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

-keep class com.taobao.**{*;}
-dontwarn  com.taobao.**

-keep class android.net.**{*;}
-dontwarn android.net.**

-keep class org.apache.http.**{*;}
-dontwarn org.apache.http.**

-keep class com.huawei.**{*;}
-dontwarn com.huawei.**

-keep class android.os.**{*;}
-dontwarn android.os.**

-keep class android.app.**{*;}
-dontwarn android.app.**

-keep class okio.**{*;}
-dontwarn okio.**

-keep class android.util.**{*;}
-dontwarn android.util.**

-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {* ;}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    public void get*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

#-keepnames class * implements java.io.Serializable

-keep public class * implements java.io.Serializable {
    public *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#------------------------

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#------------------

#极光
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
#-----------------
-keep class com.chad.library.**{*;}
-keep class com.nostra13.universalimageloader.**{*;}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(Java.lang.Throwable);
}
#------------------------------------
-keep class com.sjcdjsq.app.bean.**{*;}
-keep class com.sjcdjsq.libs.bean.**{*;}
-keep class com.sjcdjsq.libs.net.basehttp.**{*;}
-keep class com.sjcdjsq.app.models.**{*;}
-keep class com.sjcdjsq.app.net.api.**{*;}
-keep class com.sjcdjsq.app.widgets.Dialog.**{*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.lib.share.bean.**{*;}
-keep class com.lib.login.**{*;}
-keep class app.dinus.com.loadingdrawable.render.**{*;}

#权限
-dontwarn com.yanzhenjie.permission.**
# 嵌入广点通sdk时必须添加
-keep class com.qq.e.** {
    public protected *;
}

# 嵌入广点通sdk时必须添加
-keep class android.support.v4.**{ *;}

# Demo工程里用到了AQuery库，因此需要添加下面的配置
# 请开发者根据自己实际情况给第三方库的添加相应的混淆设置
-dontwarn com.androidquery.**
-keep class com.androidquery.** { *;}

# bugly 代码混淆
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }

