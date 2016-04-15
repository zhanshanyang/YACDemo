package com.yangaiche.yackeeper.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by ui on 16/4/13.
 */
public class Utils {


    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if(packageInfo != null)
            return packageInfo.versionName;
        return "";
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
