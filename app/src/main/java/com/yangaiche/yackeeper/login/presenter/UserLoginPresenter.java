package com.yangaiche.yackeeper.login.presenter;

import android.text.TextUtils;

import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.login.model.IUserLoginModel;
import com.yangaiche.yackeeper.login.model.UserLoginModel;
import com.yangaiche.yackeeper.login.view.IUserLoginView;

/**
 * Created by mr_yang on 16-3-18.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView>{

    private IUserLoginModel iUserLoginModel;

    public UserLoginPresenter(){
        iUserLoginModel = new UserLoginModel();
    }

    public void login(String userName, String userPassword) {
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)){
            return;
        }


    }
}
