package com.yangaiche.yackeeper.order.presenter;

import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.base.MyApplication;
import com.yangaiche.yackeeper.bean.Order;
import com.yangaiche.yackeeper.bean.ResponseBean;
import com.yangaiche.yackeeper.order.model.IOrderModel;
import com.yangaiche.yackeeper.order.model.OrderCallBack;
import com.yangaiche.yackeeper.order.model.OrderModel;
import com.yangaiche.yackeeper.order.view.IOrderCommentView;
import com.yangaiche.yackeeper.utils.ToastTip;

import okhttp3.Call;

/**
 * Created by ui on 16/5/3.
 */
public class OrderCommentPresenter extends BasePresenter<IOrderCommentView> {
    private IOrderModel orderModel;

    public OrderCommentPresenter(){
        orderModel = new OrderModel();
    }

    public void loadOrderData(String userType, Long mOrder_id) {
        getView().showProgress();
        orderModel.getOrderData(userType, mOrder_id, MyApplication.getInstance().getUserId(), new OrderCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                ToastTip.show("onError");
            }

            @Override
            public void onResponse(ResponseBean<Order> response) {
                if(response != null && response.isSuccess() && response.data != null){
                    getView().updateOrderData(response.data);
                }else if(response != null){
                    ToastTip.show(response.message);
                }
            }

            @Override
            public void onAfter() {
                getView().dismissProgress();
            }
        });
    }
}
