package com.yangaiche.yackeeper.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.bean.Product;

import java.util.List;

/**
 * Created by ui on 16/5/3.
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;
    private int[] normalPics = new int[] { R.drawable.grid_pick_car, R.drawable.grid_first_check, R.drawable.grid_all_check, R.drawable.grid_return_car };
    private int[] completePics = new int[] { R.drawable.grid_pick_car_after, R.drawable.grid_first_check_after, R.drawable.grid_all_check_after, R.drawable.grid_return_car_after };
    private boolean suppliesFlag, additionsFlag;

    public OrderDetailsAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    public void updateData(List<Product> products, boolean suppliesFlag, boolean additionsFlag){
        this.products = products;
        this.suppliesFlag = suppliesFlag;
        this.additionsFlag = additionsFlag;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = getItem(position);
        if (position <= 3) {// 接车
            holder.product_img.setImageResource(product.complete ? completePics[position]: normalPics[position]);
        } else if (position == 4 && suppliesFlag) {
            holder.product_img.setImageResource(product.id.intValue());
        } else if (additionsFlag && position == getItemCount()-1){//有增项
            holder.product_img.setImageResource(product.complete ? R.drawable.grid_additions_after:R.drawable.grid_addition);
        } else {
            holder.product_img.setImageResource(product.complete ? R.drawable.grid_check_after_icon:R.drawable.grid_check_icon);
        }
        StringBuffer buffer = new StringBuffer();
        if(product.product_categories != null && product.product_categories.size() > 0){
            String cate_str = product.product_categories.get(product.product_categories.size() -1);
            buffer.append(cate_str).append("-");
        }
        buffer.append(product.product_name);
        holder.product_tv.setText(buffer.toString());
    }

    private Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            product_img = (ImageView) itemView.findViewById(R.id.product_img);
            product_tv = (TextView) itemView.findViewById(R.id.product_tv);
        }
    }
}
