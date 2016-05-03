package com.yangaiche.yackeeper.order.model;

import com.yangaiche.yackeeper.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ui on 16/5/3.
 */
public class OrderModel implements IOrderModel {

    public static String ORDERS_SUBURL = "/orders.json";

    @Override
    public void getOrderData(String userType, Long order_id, Long user_id, OrderCallBack orderCallBack) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_type", userType);
        map.put("order_id", String.valueOf(order_id));
        map.put("keeper_id", String.valueOf(user_id));
        NetUtils.get(ORDERS_SUBURL, 3, map, orderCallBack, true);
    }
}
