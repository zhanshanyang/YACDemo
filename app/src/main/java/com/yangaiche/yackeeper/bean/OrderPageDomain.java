package com.yangaiche.yackeeper.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
public class OrderPageDomain implements Parcelable {

    public Integer cur_page;
    public Integer page_size;
    public Integer total_size;
    public Integer total_page;
    public List<CarKeeperOrder> items;

    protected OrderPageDomain(Parcel in) {
    }

    public static final Creator<OrderPageDomain> CREATOR = new Creator<OrderPageDomain>() {
        @Override
        public OrderPageDomain createFromParcel(Parcel in) {
            return new OrderPageDomain(in);
        }

        @Override
        public OrderPageDomain[] newArray(int size) {
            return new OrderPageDomain[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cur_page);
        dest.writeInt(page_size);
        dest.writeInt(total_size);
        dest.writeInt(total_page);
        dest.writeTypedList(items);
    }
}
