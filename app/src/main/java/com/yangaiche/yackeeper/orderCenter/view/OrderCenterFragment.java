package com.yangaiche.yackeeper.orderCenter.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.orderCenter.adapter.TabFragmentPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderCenterFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.tab_order_center)
    TabLayout tab_order_center;
    @Bind(R.id.viewPager_order_center)
    ViewPager viewPager_order_center;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderCenterFragment() { }

    public static OrderCenterFragment newInstance(String param1, String param2) {
        OrderCenterFragment fragment = new OrderCenterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_center, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(getFragmentManager());
        tabFragmentPagerAdapter.addTab(new OrderUnCompleteFragment(), "未完成订单");
        tabFragmentPagerAdapter.addTab(new OrderCompleteFragment(), "已完成订单");
        viewPager_order_center.setAdapter(tabFragmentPagerAdapter);
        viewPager_order_center.setOffscreenPageLimit(2);
        tab_order_center.setupWithViewPager(viewPager_order_center);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * 调用父activity接口
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
