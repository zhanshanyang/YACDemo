package com.yangaiche.yackeeper.login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.bean.ResponseBean;
import com.yangaiche.yackeeper.bean.UserAccount;
import com.yangaiche.yackeeper.login.model.IUserLoginModel;
import com.yangaiche.yackeeper.login.model.UserCallBack;
import com.yangaiche.yackeeper.login.model.UserLoginModel;
import com.yangaiche.yackeeper.login.view.IUserLoginView;
import com.yangaiche.yackeeper.utils.MySharedPreferences;
import com.yangaiche.yackeeper.utils.ToastTip;

import okhttp3.Call;

/**
 * Created by mr_yang on 16-3-18.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView>{
    private IUserLoginModel iUserLoginModel;

    public UserLoginPresenter(){
        iUserLoginModel = new UserLoginModel();
    }

    public void login(final Context context, String userName, String userPassword) {
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)){
            return;
        }
        getView().showProgressDialog();
        iUserLoginModel.userLogin(userName, userPassword, new UserCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                e.printStackTrace();
                ToastTip.show("onError");
            }

            @Override
            public void onResponse(ResponseBean<UserAccount> response) {
                if(response != null && response.isSuccess()){
                    MySharedPreferences.getInstance(context).saveClass4String(MySharedPreferences.USER_ACCOUNT_SP, response.data);
                    getView().loginSuccess();
                }else if(response != null){
                    ToastTip.show(response.message);
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                getView().hideProgressDialog();
            }
        });
    }
}
