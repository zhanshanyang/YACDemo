package com.yangaiche.yackeeper.utils;

import com.yangaiche.yackeeper.base.Constants;
import com.yangaiche.yackeeper.base.MyApplication;
import com.yangaiche.yackeeper.bean.BaseCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by ui on 16/4/13.
 */
public class NetUtils {


    /**
     * @param suburl        子url
     * @param json_str      传递的文本
     * @param urlVersion    本接口是哪个版本
     * @param needToken     true:需要token false:不需要token
     * @param callBack  返回调用
     */
    public static void postString(String suburl, int urlVersion, String json_str, BaseCallBack callBack, boolean needToken) {
        String url = getUrl(suburl, urlVersion);
        OkHttpUtils.postString()
                .url(url)
                .mediaType(getMediaType())
                .headers(getHeaders(needToken))
                .content(json_str)
                .tag(suburl)
                .build()
                .execute(callBack);
    }

    public static void get(String suburl, int urlVersion, Map<String, String> params, BaseCallBack callback, boolean needToken){
        String url = getUrl(suburl, urlVersion);
        OkHttpUtils.get()
                .url(url)
                .headers(getHeaders(needToken))
                .params(params)
                .tag(suburl)
                .build()
                .execute(callback);
    }

    private static String getUrl(String suburl, int urlVersion) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Constants.ROOT_URL).append(urlVersion).append(Constants.SECOND_URL).append(suburl);
        return buffer.toString();
    }

    private static Map<String, String> getHeaders(boolean needToken) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("API-Client-Device-Type", "android");
        headers.put("API-Client-App-Name", "yangaiche_keeper");
        headers.put("API-Client-App-Version",  MyApplication.getInstance().getVersionName());
        if(needToken)
            headers.put("API-Access-Token", MyApplication.getInstance().getUserToken());
        return headers;
    }

    private static MediaType getMediaType() {
        return MediaType.parse("application/json; charset=utf-8");
    }

}
