package com.yangaiche.yackeeper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by mr_yang on 16-3-21.
 */
public class MySharedPreferences<T> {

    public static final String USER_ACCOUNT_SP = "user_account_sp";
    public static final String APP_VERSION_NAME_SP = "app_version_name_sp";
    private static MySharedPreferences mySharedPreferences;
    private static SharedPreferences mSP;

    private MySharedPreferences(){}

    public static MySharedPreferences getInstance(Context context){
        if(mySharedPreferences == null){
            synchronized (MySharedPreferences.class) {
                if(mySharedPreferences == null) {
                    mySharedPreferences = new MySharedPreferences();
                    mSP = PreferenceManager.getDefaultSharedPreferences(context);
                }
            }
        }
        return mySharedPreferences;
    }

    public String getStr(String tag){
        return mSP.getString(tag, "");
    }

    public boolean saveStr(String tag, String str) {
        return mSP.edit().putString(tag, str).commit();
    }

    /**
     * 通过泛型T，获取sp中存储内容
     * @param tag
     * @param tClass
     * @return
     */
    public T getString4Class(String tag, Class<T> tClass){
        String str = getStr(tag);
        T t = new Gson().fromJson(str, tClass);
        return t;
    }

    /**
     * 存储object类的内容至默认sharedPreference
     * @param tag
     * @param object
     * @return
     */
    public boolean saveClass4String(String tag, Object object){
        String str = new Gson().toJson(object);
        return saveStr(tag, str);
    }

}
