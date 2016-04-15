package com.yangaiche.yackeeper.utils;

import com.yangaiche.yackeeper.login.model.UserCallBack;

/**
 * Created by ui on 16/4/13.
 */
public class NetUtils {


    /**
     * @param suburl        子url
     * @param json_str      传递的文本
     * @param urlVersion    本接口是哪个版本
     * @param needToken     true:需要token false:不需要token
     * @param userCallBack  返回调用
     */
    public static void postString(String suburl, URLVersion urlVersion, String json_str, boolean needToken, UserCallBack userCallBack) {
        String url = getUrl(suburl, urlVersion);
    }

    private static String getUrl(String suburl, URLVersion urlVersion) {
        switch (urlVersion){
            case URL_VERSION_ONE:
                break;
            case URL_VERSION_TWO:
                break;
            case URL_VERSION_THREE:
                break;
        }

        return null;
    }
}
