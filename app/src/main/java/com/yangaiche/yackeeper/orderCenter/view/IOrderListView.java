package com.yangaiche.yackeeper.orderCenter.view;

import com.yangaiche.yackeeper.bean.OrderPageDomain;

/**
 * Created by ui on 16/4/25.
 */
public interface IOrderListView {

    void showProgress();

    void dismissProgress();

    void refreshOrdersView(OrderPageDomain data);

    void stopRefresh();

    void stopLoadMore();

    void loadMoreEnable(boolean b);

    void addOrdersView(OrderPageDomain data);
}
