package com.yangaiche.yackeeper.orderCenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.Constants;
import com.yangaiche.yackeeper.bean.CarKeeperOrder;
import com.yangaiche.yackeeper.utils.TimeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ui on 16/4/25.
 */
public class UnCompleteOrderAdapter extends RecyclerView.Adapter<UnCompleteOrderAdapter.ViewHolder> implements Constants{
    private Context context;
    private List<CarKeeperOrder> datas;
    private Map<String, String> mStatusMap = new HashMap<String, String>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.order_id_tv)
        TextView order_id_tv;
        @Bind(R.id.order_status_tv)
        TextView order_status_tv;
        @Bind(R.id.date_time_tv)
        TextView date_time_tv;
        @Bind(R.id.segment_time_tv)
        TextView segment_time_tv;
        @Bind(R.id.time_tv)
        TextView time_tv;
        @Bind(R.id.customer_name_tv)
        TextView customer_name_tv;
        @Bind(R.id.car_number_tv)
        TextView car_number_tv;
        @Bind(R.id.car_model_tv)
        TextView car_model_tv;
        @Bind(R.id.order_consulted_tv)
        TextView order_consulted_tv;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public UnCompleteOrderAdapter(Context context, List<CarKeeperOrder> datas){
        this.context = context;
        this.datas = datas;
        mStatusMap.put(STATUS_UNCONFIRMED, "未确认");
        mStatusMap.put(STATUS_CONFIRMED, "已确认");
        mStatusMap.put(STATUS_TAKE_CAR, "接车");
        mStatusMap.put(STATUS_GIVEBACK_START, "已开始还车");
        mStatusMap.put(STATUS_GIVEBACK, "已还车");
        mStatusMap.put(STATUS_UNPAID, "未付款");
        mStatusMap.put(STATUS_COMPLETED, "已完成");
        mStatusMap.put(STATUS_EVALUATED, "已评价");
        mStatusMap.put(STATUS_CANCELLED, "已取消");
        mStatusMap.put(STATUS_CLOSED, "已关闭");
    }

    public void updateDatas(List<CarKeeperOrder> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<CarKeeperOrder> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.uncomplete_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CarKeeperOrder carKeeperOrder = datas.get(position);
        holder.order_id_tv.setText(carKeeperOrder.number);
        holder.order_status_tv.setText(mStatusMap.get(carKeeperOrder.status));
        holder.customer_name_tv.setText(carKeeperOrder.customer_name);
        holder.car_number_tv.setText(carKeeperOrder.car_number);
        holder.car_model_tv.setText(carKeeperOrder.car_model);
        if (STATUS_UNCONFIRMED.equals(carKeeperOrder.status)) {
            holder.order_consulted_tv.setText(mStatusMap.get(STATUS_UNCONFIRMED));
            holder.order_consulted_tv.setBackgroundResource(R.drawable.selector_btn);
            holder.order_consulted_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(adapterClickListener != null){
                        adapterClickListener.updateOrderStartTime(carKeeperOrder.id, position);
                    }
                }
            });
        } else {
            holder.order_consulted_tv.setText(mStatusMap.get(STATUS_CONFIRMED));
            holder.order_consulted_tv.setBackgroundResource(R.drawable.btn_bg_pressed);
            holder.order_consulted_tv.setOnClickListener(null);
        }
        String time_str = "";
        if (STATUS_COMPLETED.equals(carKeeperOrder.status)) {// to show  create_time
            holder.time_tv.setText("下单时间");
            time_str = carKeeperOrder.place_time;
        } else if (STATUS_GIVEBACK.equals(carKeeperOrder.status)) {
            holder.time_tv.setText("还车时间");
            time_str = carKeeperOrder.give_back_time;
        } else {
            holder.time_tv.setText("接车时间");
        }
        if (TextUtils.isEmpty(time_str) || "null".equals(time_str)) {
            if (carKeeperOrder.pick_start_time != null && carKeeperOrder.pick_end_time != null) {
                String[] starttimes = TimeUtils.getDateFromUTC(carKeeperOrder.pick_start_time);
                String[] endtimes = TimeUtils.getDateFromUTC(carKeeperOrder.pick_end_time);
                if (starttimes != null && starttimes.length == 2 && endtimes != null && endtimes.length == 2) {
                    holder.date_time_tv.setText(starttimes[0] + " " + starttimes[1]);
                    holder.segment_time_tv.setText(endtimes[0] + " " + endtimes[1]);
                } else {
                    holder.date_time_tv.setText("接车时间未确定");
                }
            } else if (carKeeperOrder.pick_time != null && carKeeperOrder.pick_time_segment != null) {
                holder.date_time_tv.setText(carKeeperOrder.pick_time);
                holder.segment_time_tv.setText(carKeeperOrder.pick_time_segment);
            } else {
                holder.date_time_tv.setText("接车时间未确定");
            }
        } else {
            String[] dateFromUTC = TimeUtils.getDateFromUTC(time_str);
            if (dateFromUTC != null && dateFromUTC.length == 2) {
                holder.date_time_tv.setText(dateFromUTC[0]);
                holder.segment_time_tv.setText(dateFromUTC[1]);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterClickListener != null){
                    adapterClickListener.clickItem(carKeeperOrder.id, position);
                }
            }
        });
        /*if (MyApplication.gpsOn && (carKeeperOrder.id - MyApplication.gpsOrderid) == 0) {
            holder.gps_img.setVisibility(View.VISIBLE);
        } else {
            holder.gps_img.setVisibility(View.GONE);
        }*/
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private AdapterClickListener adapterClickListener;
    public interface AdapterClickListener{
        void updateOrderStartTime(Long id, int position);
        void clickItem(Long id, int position);
    }

    public void setAdapterClickListener(AdapterClickListener listener){
        this.adapterClickListener = listener;
    }
}
