package com.huayou.acc.acc.activity;

/**
 * Created by alu on 2017/9/18.
 */

public class Ha {

    /**
     * brandName : 魅族
     * failMsg : // CRASH: com.ucaca.carlife (pid 31315)
     // Short Msg: java.lang.NullPointerException
     // Long Msg: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String com.ucaca.carlife.bean.User$DataBean.getSession_id()' on a null object reference
     // Build Label: Meizu/meizu_PRO6/PRO6:6.0/MRA58K/1465802315:user/release-keys
     // Build Changelist: 1465802315
     // Build Time: 1465802768000
     // java.lang.RuntimeException: Unable to start activity ComponentInfo{com.ucaca.carlife/com.ucaca.carlife.activity.NewsActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String com.ucaca.carlife.bean.User$DataBean.getSession_id()' on a null object reference
     // 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2697)
     // 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2768)
     // 	at android.app.ActivityThread.-wrap11(ActivityThread.java)
     // 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1582)
     // 	at android.os.Handler.dispatchMessage(Handler.java:111)
     // 	at android.os.Looper.loop(Looper.java:207)
     // 	at android.app.ActivityThread.main(ActivityThread.java:5910)
     // 	at java.lang.reflect.Method.invoke(Native Method)
     // 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:918)
     // 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:779)
     // Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String com.ucaca.carlife.bean.User$DataBean.getSession_id()' on a null object reference
     // 	at com.ucaca.carlife.activity.NewsActivity.getNews(Unknown Source)
     // 	at com.ucaca.carlife.activity.NewsActivity.initView(Unknown Source)
     // 	at com.ucaca.carlife.activity.BaseActivity.onCreate(Unknown Source)
     // 	at android.app.Activity.performCreate(Activity.java:6372)
     // 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1124)
     // 	at cn.jiguang.a.a.c.a.a.d.callActivityOnCreate(Unknown Source)
     // 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2650)
     // 	... 9 more
     //

     * modelName : PRO 6
     * osVersion : v1.1.0
     */

    private String brandName;
    private String failMsg;
    private String modelName;
    private String osVersion;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
