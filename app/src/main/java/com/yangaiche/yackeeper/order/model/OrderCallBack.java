package com.yangaiche.yackeeper.order.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.yangaiche.yackeeper.bean.BaseCallBack;
import com.yangaiche.yackeeper.bean.Order;
import com.yangaiche.yackeeper.bean.ResponseBean;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by ui on 16/5/3.
 */
public abstract class OrderCallBack extends BaseCallBack<ResponseBean<Order>> {
    @Override
    public ResponseBean<Order> parseNetworkResponse(Response response) throws Exception {
        String content = response.body().string();
        Type type = new TypeToken<ResponseBean<Order>>(){}.getType();
        try {
            return new Gson().fromJson(content, type);
        }catch (JsonSyntaxException e){
            return parseErrorNetWorkResponse(content);
        }
    }
}
