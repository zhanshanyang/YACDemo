package com.yangaiche.yackeeper.base;

import android.app.Application;
import android.content.Context;

import com.yangaiche.yackeeper.utils.Utils;

/**
 * Created by ui on 16/4/13.
 */
public class MyApplication extends Application{

    private String versionName;
    private static MyApplication instance;
    private static Context context;

    public static MyApplication getInstance(){
        if(instance == null){
            synchronized (MyApplication.class){
                if (instance == null)
                    instance = new MyApplication();
            }
        }
        return instance;
    }

    public static Context getContext(){
        if(context == null){
            context = getInstance();
        }
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

        //在每次打开应用时，保存检查版本信息
        versionName = Utils.getVersionName(this);
//        new LocationTip(this).doStartLocation();
    }

    public String getVersionName(){
        return versionName;
    }

}