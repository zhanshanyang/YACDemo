package com.yangaiche.yackeeper.orderCenter.view;

import com.yangaiche.yackeeper.bean.UserAccount;

/**
 * Created by ui on 16/4/20.
 */
public interface IMainView {

    void finishView();
    void refreshUserView(UserAccount userAccount);

    void switch2OrderCenter();

    void switch2UserCenter();

    void switch2Leave();

    void switch2Version();

    void switch2Setting();

    void logout();
}
