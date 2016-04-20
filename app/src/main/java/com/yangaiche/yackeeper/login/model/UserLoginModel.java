package com.yangaiche.yackeeper.login.model;

import com.google.gson.Gson;
import com.yangaiche.yackeeper.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr_yang on 16-3-18.
 */
public class UserLoginModel implements IUserLoginModel {

    private static String USER_LOGIN_URL = "/car_keeper/sign_in.json";

    @Override
    public void userLogin(String userName, String userPassword, UserCallBack userCallBack) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", userName);
        params.put("password", userPassword);
        String json_str = new Gson().toJson(params);
        NetUtils.postString(USER_LOGIN_URL, 1, json_str, userCallBack, true);
    }
}
