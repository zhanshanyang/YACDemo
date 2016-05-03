package com.yangaiche.yackeeper.orderCenter.model;

/**
 * Created by ui on 16/4/25.
 */
public interface IOrderCompleteModel {

    void getOrderData(String status, int page, int page_size, OrderCompleteCallBack orderCompleteCallBack);

    void updateOrderStartTime(Long id, String statusConfirmed, String note, OrderUpdateCallBack orderUpdateCallBack);
}
