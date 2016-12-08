package com.longwu.ijkplayer;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class APP extends Application {
    Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
        PushAgent mPushAgent = PushAgent.getInstance(ctx);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("mytoken", "0000000000000000:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("mytoken", s+"=="+s1);
            }
        });
    }


}
