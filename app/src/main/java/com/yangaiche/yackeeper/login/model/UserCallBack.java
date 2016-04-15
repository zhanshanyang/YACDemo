package com.yangaiche.yackeeper.login.model;

import com.google.gson.Gson;
import com.yangaiche.yackeeper.bean.UserACcount;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by ui on 16/4/13.
 */
public abstract class UserCallBack extends Callback<UserACcount> {

    @Override
    public UserACcount parseNetworkResponse(Response response) throws Exception {
        String content = response.body().string();
        return new Gson().fromJson(content, UserACcount.class);
    }
}
