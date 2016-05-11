package com.yangaiche.yackeeper.login.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.yangaiche.yackeeper.bean.BaseCallBack;
import com.yangaiche.yackeeper.bean.ResponseBean;
import com.yangaiche.yackeeper.bean.UserAccount;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by ui on 16/4/13.
 */
public abstract class UserCallBack extends BaseCallBack<ResponseBean<UserAccount>> {

    @Override
    public ResponseBean<UserAccount> parseNetworkResponse(Response response) throws Exception {
        String content = response.body().string();
        Type type = new TypeToken<ResponseBean<UserAccount>>(){}.getType();
        try {
            return new Gson().fromJson(content, type);
        }catch (JsonSyntaxException e){
            return parseErrorNetWorkResponse(content);
        }
    }


}
