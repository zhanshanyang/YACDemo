package com.yangaiche.yackeeper.order.view;

import com.yangaiche.yackeeper.bean.Order;

/**
 * Created by ui on 16/5/3.
 */
public interface IOrderCommentView  {

    void showProgress();

    void dismissProgress();

    void updateOrderData(Order data);
}
