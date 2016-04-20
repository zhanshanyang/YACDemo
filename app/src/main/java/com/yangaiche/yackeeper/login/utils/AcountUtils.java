package com.yangaiche.yackeeper.login.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.yangaiche.yackeeper.bean.UserAccount;
import com.yangaiche.yackeeper.login.view.UserLoginActivity;
import com.yangaiche.yackeeper.orderCenter.view.MainActivity;
import com.yangaiche.yackeeper.utils.MySharedPreferences;

/**
 * Created by mr_yang on 16-3-21.
 */
public class AcountUtils {

    /**
     * 判断用户是否有可使用账户,不能使用，则打开登录界面
     * @param activity
     */
    public static boolean ensureAccountAvailability(Activity activity) {
        boolean accountAvailable = hasAccount(activity);
        if(!accountAvailable) {
            addAccount(activity);
        }
        return accountAvailable;
    }

    public static boolean hasAccount(Context context) {
        UserAccount userACcount = (UserAccount) MySharedPreferences.getInstance(context).getString4Class(MySharedPreferences.USER_ACCOUNT_SP, UserAccount.class);
        return userACcount != null && userACcount.token != null;
    }

    public static void addAccount(Activity activity) {
        Intent onAddedIntent = new Intent(activity, MainActivity.class);
        activity.startActivity(UserLoginActivity.makeIntent(activity, onAddedIntent));
    }

}
