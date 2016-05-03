package com.yangaiche.yackeeper.order.model;

/**
 * Created by ui on 16/5/3.
 */
public interface IOrderModel {

    /**
     * 获取订单详细信息
     * @param userType
     * @param mOrder_id
     * @param userId
     * @param orderCallBack
     */
    void getOrderData(String userType, Long mOrder_id, Long userId, OrderCallBack orderCallBack);
}
