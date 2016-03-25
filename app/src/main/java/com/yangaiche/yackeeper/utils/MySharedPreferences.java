package com.yangaiche.yackeeper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * Created by mr_yang on 16-3-21.
 */
public class MySharedPreferences<T> {

    public static final String USER_ACCOUNT_SP = "user_account_sp";
    private static MySharedPreferences mySharedPreferences;
    private SharedPreferences mSharedPreferences;

    private MySharedPreferences(){}

    public static MySharedPreferences getInstance(){
        if(mySharedPreferences == null){
            mySharedPreferences = new MySharedPreferences();
        }
        return mySharedPreferences;
    }

    /**
     * 通过泛型T，获取sp中存储内容
     * @param context
     * @param tag
     * @param tClass
     * @return
     */
    public T getString4Class(Context context, String tag, Class<T> tClass){
        if(mSharedPreferences == null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        String str = mSharedPreferences.getString(tag, null);
        T t = new Gson().fromJson(str, tClass);
        return t;
    }

    /**
     * 存储object类的内容至默认sharedPreference
     * @param context
     * @param tag
     * @param objects
     * @return
     */
    public boolean saveClass4String(Context context, String tag, Objects objects){
        if(mSharedPreferences == null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        String str = new Gson().toJson(objects);
        return mSharedPreferences.edit().putString(tag, str).commit();
    }

}
