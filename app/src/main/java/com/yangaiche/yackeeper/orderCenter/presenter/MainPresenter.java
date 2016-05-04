package com.yangaiche.yackeeper.orderCenter.presenter;

import android.content.Context;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.bean.UserAccount;
import com.yangaiche.yackeeper.login.utils.AcountUtils;
import com.yangaiche.yackeeper.orderCenter.view.IMainView;

/**
 * Created by ui on 16/4/20.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    /**
     * 检查是否有登录用户，没有的话，自动打开登录界面，并关闭当前页面
     * @param context
     */
    public void judgeUserLogin(Context context){
        if(!AcountUtils.ensureAccountAvailability(context)){
            getView().finishView();
        }
    }

    public void getUser2View(Context context) {
        UserAccount userAccount = AcountUtils.getUserAccount(context);
        getView().refreshUserView(userAccount);
    }

    public void switch2Nav(int id) {
        if(id == R.id.order_center) {
            getView().switch2OrderCenter();
        } else if (id == R.id.nav_user_center) {
            getView().switch2UserCenter();
        } else if (id == R.id.nav_leave) {
            getView().switch2Leave();
        } else if (id == R.id.nav_version) {
            getView().switch2Version();
        } else if (id == R.id.nav_settings) {
            getView().switch2Setting();
        } else if (id == R.id.nav_logout){
            getView().logout();
        }

    }
}
