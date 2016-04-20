package com.yangaiche.yackeeper.utils;

import android.widget.Toast;

import com.yangaiche.yackeeper.base.MyApplication;

/**
 * Created by ui on 16/4/19.
 */
public class ToastTip {

    static Toast mToast;

    public static void show(CharSequence message) {
        if (mToast == null) {
            int duration = Toast.LENGTH_SHORT;
            if (message.length() > 10) {
                duration = Toast.LENGTH_LONG;
            }
            mToast = Toast.makeText(MyApplication.getInstance(), message, duration);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

}
