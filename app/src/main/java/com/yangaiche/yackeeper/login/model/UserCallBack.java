package com.yangaiche.yackeeper.login.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.yangaiche.yackeeper.bean.ResponseBean;
import com.yangaiche.yackeeper.bean.UserAccount;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by ui on 16/4/13.
 */
public abstract class UserCallBack extends Callback<ResponseBean<UserAccount>> {

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

    private ResponseBean parseErrorNetWorkResponse(String content){
        try {
            JSONObject jsonObject = new JSONObject(content);
            String code = jsonObject.optString(ResponseBean.CODE_KEY);
            String message = jsonObject.optString(ResponseBean.MESSAGE_KEY);
            return new ResponseBean(code, message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
