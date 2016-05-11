package com.yangaiche.yackeeper.orderCenter.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.yangaiche.yackeeper.bean.BaseCallBack;
import com.yangaiche.yackeeper.bean.ResponseBean;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by ui on 16/4/25.
 */
public abstract class OrderUpdateCallBack extends BaseCallBack<ResponseBean<String>> {
    @Override
    public ResponseBean<String> parseNetworkResponse(Response response) throws Exception {
        String content = response.body().string();
        Type type = new TypeToken<ResponseBean<String>>(){}.getType();
        try {
            return new Gson().fromJson(content, type);
        }catch (JsonSyntaxException e){
            return parseErrorNetWorkResponse(content);
        }
    }

}
