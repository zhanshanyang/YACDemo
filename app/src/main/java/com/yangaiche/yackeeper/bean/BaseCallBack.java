package com.yangaiche.yackeeper.bean;


import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ui on 16/4/25.
 */
public abstract class BaseCallBack<T> extends Callback<T> {

    protected ResponseBean parseErrorNetWorkResponse(String content){
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
