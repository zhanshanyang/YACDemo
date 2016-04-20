package com.yangaiche.yackeeper.login.utils;

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
     * @param context
     */
    public static boolean ensureAccountAvailability(Context context) {
        UserAccount userAccount = getUserAccount(context);
        boolean accountAvailable = (userAccount != null && userAccount.token != null);
        if(!accountAvailable) {
            addAccount(context);
        }
        return accountAvailable;
    }

    public static UserAccount getUserAccount(Context context){
        return (UserAccount) MySharedPreferences.getInstance(context).getString4Class(MySharedPreferences.USER_ACCOUNT_SP, UserAccount.class);
    }

    public static void addAccount(Context context) {
        Intent onAddedIntent = new Intent(context, MainActivity.class);
        context.startActivity(UserLoginActivity.makeIntent(context, onAddedIntent));
    }

}
