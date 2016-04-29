package com.yangaiche.yackeeper.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.yangaiche.yackeeper.bean.UserAccount;
import com.yangaiche.yackeeper.utils.MySharedPreferences;
import com.yangaiche.yackeeper.utils.Utils;

/**
 * Created by ui on 16/4/13.
 */
public class MyApplication extends Application{

    private String versionName;
    private UserAccount userAccount;
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
        userAccount = getUser();
    }

    private UserAccount getUser() {
        UserAccount userAccount = (UserAccount) MySharedPreferences.getInstance(this).getString4Class(MySharedPreferences.USER_ACCOUNT_SP, UserAccount.class);
        return userAccount;
    }

    public String getVersionName(){
        if(TextUtils.isEmpty(versionName))
            versionName = Utils.getVersionName(this);
        return versionName;
    }

    public String getUserToken(){
        if(userAccount == null){
            userAccount = getUser();
        }
        return userAccount != null ? userAccount.token : null;
    }

    public UserAccount getUserAccount(){
        if(userAccount == null)
            userAccount = getUser();
        return userAccount;
    }
}