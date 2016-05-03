package com.yangaiche.yackeeper.orderCenter.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.BaseFragment;
import com.yangaiche.yackeeper.bean.CarKeeperOrder;
import com.yangaiche.yackeeper.bean.OrderPageDomain;
import com.yangaiche.yackeeper.order.view.OrderCommentActivity;
import com.yangaiche.yackeeper.order.view.OrderDetailsActivity;
import com.yangaiche.yackeeper.orderCenter.adapter.CompleteOrderAdapter;
import com.yangaiche.yackeeper.orderCenter.presenter.OrderListPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class OrderCompleteFragment extends BaseFragment<IOrderListView, OrderListPresenter> implements IOrderListView, CompleteOrderAdapter.AdapterClickListener {
    private static String ORDER_STATUS = "completed";
    @Bind(R.id.total_num_tv)
    TextView total_num_tv;
    @Bind(R.id.current_page_tv)
    TextView current_page_tv;
    @Bind(R.id.recyclerview)
    XRecyclerView recyclerView;
    @Bind(R.id.text_empty)
    TextView text_empty;
    @Bind(R.id.progress)
    MaterialProgressBar progressBar;
    private Activity activity;

    private CompleteOrderAdapter mAdapter;

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
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.setLoadingMoreEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter = new CompleteOrderAdapter(activity, new ArrayList<CarKeeperOrder>());
        mAdapter.setAdapterClickListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setEmptyView(text_empty);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        recyclerView.setRefreshing(true);
    }

    private void refresh(){
        mPresenter.refresh(ORDER_STATUS, 1);
    }

    private void loadMore(){
        mPresenter.loadMore(ORDER_STATUS);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
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
        recyclerView.refreshComplete();
    }

    @Override
    public void stopLoadMore() {
        recyclerView.loadMoreComplete();
    }

    @Override
    public void loadMoreEnable(boolean b) {
        recyclerView.setLoadingMoreEnabled(b);
    }

    @Override
    protected OrderListPresenter createPresenter() {
        return new OrderListPresenter();
    }

    @Override
    public void openCommentActivity(Long id, int position) {
        Intent intent = OrderCommentActivity.makeIntent(activity, id);
        startActivity(intent);
    }

    @Override
    public void clickItem(Long id, int position) {
        Intent intent = OrderDetailsActivity.makeIntent(activity, id, 0);
        startActivity(intent);
    }
}
