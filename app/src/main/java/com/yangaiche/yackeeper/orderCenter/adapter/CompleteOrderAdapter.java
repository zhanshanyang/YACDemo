package com.yangaiche.yackeeper.orderCenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.bean.CarKeeperOrder;
import com.yangaiche.yackeeper.utils.TimeUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ui on 16/4/25.
 */
public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.ViewHolder> {
    private Context context;
    private List<CarKeeperOrder> datas;


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

    public CompleteOrderAdapter(Context context, List<CarKeeperOrder> datas){
        this.context = context;
        this.datas = datas;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CarKeeperOrder carKeeperOrder = datas.get(position);
        holder.order_id_tv.setText(carKeeperOrder.number);
        holder.order_status_tv.setText("已完成");
        holder.customer_name_tv.setText(carKeeperOrder.customer_name);
        holder.car_number_tv.setText(carKeeperOrder.car_number);
        holder.car_model_tv.setText(carKeeperOrder.car_model);
        holder.date_time_tv.setText("");
        holder.segment_time_tv.setText("");
        if (!TextUtils.isEmpty(carKeeperOrder.end_time)) {
            String[] dateFromUTC = TimeUtils.getDateFromUTC(carKeeperOrder.end_time);
            if (dateFromUTC != null && dateFromUTC.length == 2) {
                holder.date_time_tv.setText(dateFromUTC[0]);
                holder.segment_time_tv.setText(dateFromUTC[1]);
            }
        }
        holder.time_tv.setText("完成时间");
        holder.order_consulted_tv.setVisibility(View.VISIBLE);
        holder.order_consulted_tv.setText("查看评论");
        /*holder.order_consulted_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderCommentActivity.class);
                intent.putExtra(ORDER_ID, carKeeperOrder.id);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
