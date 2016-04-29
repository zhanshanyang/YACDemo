package com.yangaiche.yackeeper.orderCenter.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.BaseFragment;
import com.yangaiche.yackeeper.bean.CarKeeperOrder;
import com.yangaiche.yackeeper.bean.OrderPageDomain;
import com.yangaiche.yackeeper.orderCenter.adapter.UnCompleteOrderAdapter;
import com.yangaiche.yackeeper.orderCenter.presenter.OrderListPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class OrderUnCompleteFragment extends BaseFragment<IOrderListView, OrderListPresenter> implements IOrderListView {
    private static String ORDER_STATUS = "uncompleted";
    @Bind(R.id.refresh)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.total_num_tv)
    TextView total_num_tv;
    @Bind(R.id.current_page_tv)
    TextView current_page_tv;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.progress)
    MaterialProgressBar progress;
    private Activity activity;

    private UnCompleteOrderAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = getActivity();
        View view = inflater.inflate(R.layout.refresh_load_recycleview, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mAdapter = new UnCompleteOrderAdapter(activity, new ArrayList<CarKeeperOrder>());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                refresh();
            }

            @Override
            public void onfinish() {
                Toast.makeText(activity, "finish", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.loadMore(ORDER_STATUS);
            }
        });
        refresh();
    }

    private void refresh(){
        mPresenter.refresh(ORDER_STATUS, 1);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void refreshOrdersView(OrderPageDomain domain) {
        total_num_tv.setText(getString(R.string.total_num_str, domain.total_size));
        current_page_tv.setText(getString(R.string.current_page_str, domain.cur_page, domain.total_page));
        mAdapter.updateDatas(domain.items);
    }

    @Override
    public void addOrdersView(OrderPageDomain domain) {
        total_num_tv.setText(getString(R.string.total_num_str, domain.total_size));
        current_page_tv.setText(getString(R.string.current_page_str, domain.cur_page, domain.total_page));
        mAdapter.addDatas(domain.items);
    }

    @Override
    public void stopRefresh() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void stopLoadMore() {
        refreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void loadMoreEnable(boolean b) {
        refreshLayout.setLoadMore(b);
    }

    @Override
    protected OrderListPresenter createPresenter() {
        return new OrderListPresenter();
    }
}
