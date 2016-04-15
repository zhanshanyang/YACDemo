package com.yangaiche.yackeeper;

import android.app.Application;

import com.yangaiche.yackeeper.utils.MySharedPreferences;
import com.yangaiche.yackeeper.utils.Utils;

/**
 * Created by ui on 16/4/13.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //在每次开机时，保存检查版本信息
        String versionName = Utils.getVersionName(this);
        MySharedPreferences.getInstance(this).saveStr(MySharedPreferences.APP_VERSION_NAME_SP, versionName);
    }
}
