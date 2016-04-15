package com.yangaiche.yackeeper.login.model;

/**
 * Created by mr_yang on 16-3-18.
 */
public interface IUserLoginModel {

    void userLogin(String userName, String userPassword, UserCallBack userCallBack);

}
