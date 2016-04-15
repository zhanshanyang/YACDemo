package com.yangaiche.yackeeper.login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.bean.UserACcount;
import com.yangaiche.yackeeper.login.model.IUserLoginModel;
import com.yangaiche.yackeeper.login.model.UserCallBack;
import com.yangaiche.yackeeper.login.model.UserLoginModel;
import com.yangaiche.yackeeper.login.view.IUserLoginView;
import com.yangaiche.yackeeper.utils.MySharedPreferences;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by mr_yang on 16-3-18.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView>{
    private static String RootUrl = "http://dev.yangaiche.com/develop/";//测试库
    private IUserLoginModel iUserLoginModel;

    public UserLoginPresenter(){
        iUserLoginModel = new UserLoginModel();
    }

    public void login(Context context, String userName, String userPassword) {
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)){
            return;
        }
        iUserLoginModel.userLogin(userName, userPassword, new UserCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(UserACcount response) {

            }
        });


        Map<String, String> headers = new HashMap<String, String>();
        //headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("API-Client-Device-Type", "android");
        headers.put("API-Client-App-Name", "yangaiche_keeper");
        headers.put("API-Client-App-Version", MySharedPreferences.getInstance(context).getStr(MySharedPreferences.APP_VERSION_NAME_SP));
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", userName);
        params.put("password", userPassword);
        String suburl = "/car_keeper/sign_in.json";
        String url = RootUrl+"v1/api"+suburl;
        OkHttpUtils.postString()
                .url(url)
                .headers(headers)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(params))
                .tag(UserLoginPresenter.this)
                .build()
                .execute(new UserCallBack() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(UserACcount response) {

                    }
                });
    }
}
