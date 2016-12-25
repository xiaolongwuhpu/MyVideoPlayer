package com.longwu.ijkplayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.longwu.ijkplayer.utlis.CrashHandler;
import com.longwu.ijkplayer.utlis.FSScreen;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import java.util.ArrayList;

public class APP extends Application {
   public static  Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
        FSScreen.myCtx = ctx;
        PushAgent mPushAgent = PushAgent.getInstance(ctx);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("mytoken", "++++++++++++++++++++++++++++:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("mytoken", s+"=="+s1);
            }
        });

        CrashHandler ch = CrashHandler.getInstance();
        ch.init(this);
        Thread.setDefaultUncaughtExceptionHandler(ch);
    }

    ArrayList<Activity> list = new ArrayList<Activity>();

    /**
     * Activity关闭时，删除Activity列表中的Activity对象*/
    public void removeActivity(Activity a){
        list.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象*/
    public void addActivity(Activity a){
        list.add(a);
    }

    /**
     * 关闭Activity列表中的所有Activity*/
    public void finishActivity(){
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
