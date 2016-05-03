package com.yangaiche.yackeeper.order.view;

import com.yangaiche.yackeeper.bean.Order;

/**
 * Created by ui on 16/5/3.
 */
public interface IOrderDetailsView {

    void showProgress();

    void dismissProgress();

    void stopRefresh();

    void updateOrderData(Order data);
}
