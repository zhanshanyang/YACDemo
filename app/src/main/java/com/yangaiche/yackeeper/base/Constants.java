package com.yangaiche.yackeeper.base;

/**
 * Created by ui on 16/4/19.
 */
public interface Constants {

    String ROOT_URL = "http://dev.yangaiche.com/develop/v";//测试库
    String SECOND_URL = "/api";

    String GANK_DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    //order_status
    String STATUS_UNCONFIRMED = "unconfirmed";//	未确认
    String STATUS_CONFIRMED = "confirmed";//	已确认
    String STATUS_TAKE_CAR = "take";//	已接车
    String STATUS_GIVEBACK_START = "giveback_start";//	已开始还车
    String STATUS_GIVEBACK = "giveback";//	已还车
    String STATUS_UNPAID = "unpaid";//	未付款
    String STATUS_COMPLETED = "completed";//	已完成
    String STATUS_EVALUATED = "evaluated";// 	已评价
    String STATUS_CANCELLED = "cancelled";//	已取消
    String STATUS_CLOSED = "closed";//	已关闭

    String USER_TYPE = "keeper";
    String ORDER_ID = "order_id";
    String ADDITIONS_STR = "additions";
}
