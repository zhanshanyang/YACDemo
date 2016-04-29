package com.yangaiche.yackeeper.orderCenter.presenter;

import com.yangaiche.yackeeper.base.BasePresenter;
import com.yangaiche.yackeeper.bean.OrderPageDomain;
import com.yangaiche.yackeeper.bean.ResponseBean;
import com.yangaiche.yackeeper.orderCenter.model.IOrderCompleteModel;
import com.yangaiche.yackeeper.orderCenter.model.OrderCompleteCallBack;
import com.yangaiche.yackeeper.orderCenter.model.OrderCompleteModel;
import com.yangaiche.yackeeper.orderCenter.view.IOrderListView;
import com.yangaiche.yackeeper.utils.ToastTip;

import okhttp3.Call;

/**
 * Created by ui on 16/4/25.
 */
public class OrderListPresenter extends BasePresenter<IOrderListView> {

    private static final int PAGE_SIZE = 5;
    private IOrderCompleteModel orderCompleteModel;
    private Integer cur_page;
    private Integer page_size;
    private Integer total_size;
    private Integer total_page;

    public OrderListPresenter(){
        orderCompleteModel = new OrderCompleteModel();
    }
    public void refresh(String status, int page) {
        getView().showProgress();
        orderCompleteModel.getOrderData(status, page, PAGE_SIZE, new OrderCompleteCallBack(){
            @Override
            public void onError(Call call, Exception e) {
                ToastTip.show("onError");
            }

            @Override
            public void onResponse(ResponseBean<OrderPageDomain> response) {
                if(response != null && response.isSuccess() && response.data != null){
                    updatePageData(response.data);
                    getView().refreshOrdersView(response.data);
                }else if(response != null){
                    ToastTip.show(response.message);
                }
            }

            @Override
            public void onAfter() {
                getView().stopRefresh();
                getView().stopLoadMore();
                getView().dismissProgress();
            }
        });
    }

    private void updatePageData(OrderPageDomain data) {
        cur_page = data.cur_page;
        page_size = data.page_size;
        total_size = data.total_size;
        total_page = data.total_page;
        if(cur_page < total_page){
            getView().loadMoreEnable(true);
        }else{
            getView().loadMoreEnable(false);
        }
    }

    public void loadMore(String status) {
        int loadPage = cur_page +1;
        if(loadPage > total_page){
            getView().stopLoadMore();
            return;
        }
        orderCompleteModel.getOrderData(status, loadPage, PAGE_SIZE, new OrderCompleteCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                ToastTip.show("onError");
            }

            @Override
            public void onResponse(ResponseBean<OrderPageDomain> response) {
                if(response != null && response.isSuccess() && response.data != null){
                    updatePageData(response.data);
                    getView().addOrdersView(response.data);
                }else if(response != null){
                    ToastTip.show(response.message);
                }
            }

            @Override
            public void onAfter() {
                getView().stopRefresh();
            }
        });
    }
}
