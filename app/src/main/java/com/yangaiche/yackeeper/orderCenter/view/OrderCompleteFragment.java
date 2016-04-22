package com.yangaiche.yackeeper.orderCenter.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.yac.yacgj.R;
import com.yac.yacgj.activities.OrderCommentActivity;
import com.yac.yacgj.activities.OrderDetailsActivity;
import com.yac.yacgj.domain.CarKeeperOrder;
import com.yac.yacgj.domain.OrderPageDomain;
import com.yac.yacgj.icounts.App;
import com.yac.yacgj.icounts.ICounts;
import com.yac.yacgj.utils.Utils2;
import com.yac.yacgj.utils.Utils4Net;
import com.yac.yacgj.view.MyProgressDialog;
import com.yac.yacgj.view.MyToast;
import com.yac.yacgj.view.XListView;
import com.yangaiche.yackeeper.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCompleteFragment extends BaseFragment implements ICounts, OnItemClickListener, XListView.IXListViewListener {
    protected static final String TAG = "OrderCompleteFragment";
    private static final int GET_ORDERS_FIRST_WHAT = 0;
    private static final int LOAD_MORE_ORDERS_WHAT = 1;
    private TextView total_num_tv, current_page_tv;
    private XListView mListview;

    private LayoutInflater mInflater;
    private AsyncHttpClient mClient;
    private Gson mGson;
    private MyCompAdapter myCompAdapter;
    private List<CarKeeperOrder> mCompList;
    private int mCur_page = 0;
    private int mTotal_page = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String content = (String) msg.obj;
            switch (msg.what) {
                case GET_ORDERS_FIRST_WHAT:
                    if (myProgressDialog != null && myProgressDialog.isShowing()) {
                        myProgressDialog.dismiss();
                    }
                    parserJson(content);
                    mListview.stopRefresh();
                    break;
                case LOAD_MORE_ORDERS_WHAT:
                    if (myProgressDialog != null && myProgressDialog.isShowing()) {
                        myProgressDialog.dismiss();
                    }
                    parserLoadMore(content);
                    mListview.stopLoadMore();
                    break;
                case PARSERJSON_FAILURE://json解析失败
                case NET_FAILURE://联网失败
                    if (myProgressDialog != null && myProgressDialog.isShowing()) {
                        myProgressDialog.dismiss();
                    }
                    mListview.stopRefresh();
                    mListview.stopLoadMore();
                    String msg_str = (String) msg.obj;
                    MyToast.makeText(getActivity(), R.color.pay_color, msg_str, MyToast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mClient = new AsyncHttpClient();
        mGson = new Gson();
        myProgressDialog = MyProgressDialog.createDialog(getActivity());
        View view = inflater.inflate(R.layout.xlistview, null);
        total_num_tv = (TextView) view.findViewById(R.id.total_num_tv);
        current_page_tv = (TextView) view.findViewById(R.id.current_page_tv);
        mListview = (XListView) view.findViewById(R.id.listview);
        initListview();
        myProgressDialog.show();
        netCheckOrders();
        return view;
    }

    private void initListview() {
        mCompList = new ArrayList<CarKeeperOrder>();
        myCompAdapter = new MyCompAdapter(mCompList);
        mListview.setAdapter(myCompAdapter);
        mListview.setPullLoadEnable(true);
        mListview.setXListViewListener(this);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void netCheckOrders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_status", "completed");
        map.put("user_type", "keeper");
        map.put("page", "1");
        map.put("page_size", String.valueOf(PAGE_SIZE));
        map.put("keeper_id", String.valueOf(App.mCarKeeper.user_id));
        Utils4Net.get(getActivity(), mClient, ORDERS_SUBURL, map, mHandler, GET_ORDERS_FIRST_WHAT, true, "v3", null);
    }

    @Override
    public void onRefresh() {
        netCheckOrders();
    }

    @Override
    public void onLoadMore() {
        if(++mCur_page <= mTotal_page) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("order_status", "completed");
            map.put("user_type", "keeper");
            map.put("page", String.valueOf(mCur_page));
            map.put("page_size", String.valueOf(PAGE_SIZE));
            map.put("keeper_id", String.valueOf(App.mCarKeeper.user_id));
            Utils4Net.get(getActivity(), mClient, ORDERS_SUBURL, map, mHandler, LOAD_MORE_ORDERS_WHAT, true, "v3", null);
        }else{
            Message msg = mHandler.obtainMessage();
            msg.obj = "已经到底了";
            msg.what = PARSERJSON_FAILURE;
            mHandler.sendMessage(msg);
        }
    }

    private class MyCompAdapter extends BaseAdapter {
        private List<CarKeeperOrder> mCompList;

        public MyCompAdapter(List<CarKeeperOrder> compList) {
            mCompList = compList;
        }

        public void updateData(List<CarKeeperOrder> compList) {
            mCompList = compList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mCompList.size();
        }

        @Override
        public CarKeeperOrder getItem(int position) {
            return mCompList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = mInflater.inflate(R.layout.uncomplete_item, null);
                holder = new ViewHolder();
                holder.order_id_tv = (TextView) convertView.findViewById(R.id.order_id_tv);
                holder.order_status_tv = (TextView) convertView.findViewById(R.id.order_status_tv);
                holder.date_time_tv = (TextView) convertView.findViewById(R.id.date_time_tv);
                holder.segment_time_tv = (TextView) convertView.findViewById(R.id.segment_time_tv);
                holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
                holder.customer_name_tv = (TextView) convertView.findViewById(R.id.customer_name_tv);
                holder.car_number_tv = (TextView) convertView.findViewById(R.id.car_number_tv);
                holder.car_model_tv = (TextView) convertView.findViewById(R.id.car_model_tv);
                holder.order_consulted_tv = (TextView) convertView.findViewById(R.id.order_consulted_tv);
                convertView.setTag(holder);
            }
            final CarKeeperOrder carKeeperOrder = getItem(position);
            holder.order_id_tv.setText(carKeeperOrder.number);
            holder.order_status_tv.setText("已完成");
            holder.customer_name_tv.setText(carKeeperOrder.customer_name);
            holder.car_number_tv.setText(carKeeperOrder.car_number);
            holder.car_model_tv.setText(carKeeperOrder.car_model);
            if (!TextUtils.isEmpty(carKeeperOrder.end_time) && !"null".equals(carKeeperOrder.end_time)) {
                String[] dateFromUTC = Utils2.getDateFromUTC(carKeeperOrder.end_time);
                if (dateFromUTC != null && dateFromUTC.length == 2) {
                    holder.date_time_tv.setText(dateFromUTC[0]);
                    holder.segment_time_tv.setText(dateFromUTC[1]);
                    holder.time_tv.setText("完成时间");
                }
            }else{
                holder.date_time_tv.setText("");
                holder.segment_time_tv.setText("");
                holder.time_tv.setText("完成时间");
            }
            holder.order_consulted_tv.setVisibility(View.VISIBLE);
            holder.order_consulted_tv.setText("查看评论");
            holder.order_consulted_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), OrderCommentActivity.class);
                    intent.putExtra(ORDER_ID, carKeeperOrder.id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView order_id_tv, order_status_tv, date_time_tv, segment_time_tv, time_tv, customer_name_tv, car_number_tv, car_model_tv, order_consulted_tv;
        }
    }

    protected void parserJson(String content) {
        OrderPageDomain domain = mGson.fromJson(content, OrderPageDomain.class);
        if (domain != null) {
            mCur_page = domain.cur_page;
            mTotal_page = domain.total_page;
            mCompList = domain.items;
            myCompAdapter.updateData(mCompList);
            total_num_tv.setText(getString(R.string.total_num_str, domain.total_size));
            current_page_tv.setText(getString(R.string.current_page_str, domain.cur_page, domain.total_page));
        } else {
            Message msg = mHandler.obtainMessage();
            msg.obj = "解析出错";
            msg.what = PARSERJSON_FAILURE;
            mHandler.sendMessage(msg);
        }
    }

    private void parserLoadMore(String content) {
        OrderPageDomain domain = mGson.fromJson(content, OrderPageDomain.class);
        if (domain != null) {
            mCur_page = domain.cur_page;
            mTotal_page = domain.total_page;
            mCompList.addAll(domain.items);
            myCompAdapter.updateData(mCompList);
            total_num_tv.setText(getString(R.string.total_num_str, domain.total_size));
            current_page_tv.setText(getString(R.string.current_page_str, domain.cur_page, domain.total_page));
        } else {
            Message msg = mHandler.obtainMessage();
            msg.obj = "解析出错";
            msg.what = PARSERJSON_FAILURE;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        App.NeedReload = true;
        CarKeeperOrder carKeeperOrder = myCompAdapter.getItem(position - 1);
        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
        intent.putExtra(ORDER_ID, carKeeperOrder.id);
        startActivity(intent);
    }
}
