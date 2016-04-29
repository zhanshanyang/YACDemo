package com.yangaiche.yackeeper.orderCenter.model;

import com.yangaiche.yackeeper.base.MyApplication;
import com.yangaiche.yackeeper.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ui on 16/4/25.
 */
public class OrderCompleteModel implements IOrderCompleteModel {
    private static String ORDERS_SUBURL = "/orders.json";
    private static String USER_TYPE = "keeper";

    @Override
    public void getOrderData(String status, int page, int page_size, OrderCompleteCallBack orderCompleteCallBack) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_status", status);
        map.put("user_type", USER_TYPE);
        map.put("page", String.valueOf(page));
        map.put("page_size", String.valueOf(page_size));
        map.put("keeper_id", String.valueOf(MyApplication.getInstance().getUserAccount().user_id));
        NetUtils.get(ORDERS_SUBURL, 3, map, orderCompleteCallBack, true);
    }
}
