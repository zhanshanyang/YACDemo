package com.yangaiche.yackeeper.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Order implements Parcelable{

	public Long id;
	public List<Product> products;
	public List<Inspection> inspections;
	public Boolean customerEvaluated;
	public Boolean paid;
	public String number;
	public Boolean disabled;
	public Boolean committed;
	// public order_type : null
	public String service_type;
	public String peer_source;
	public Long source_order_id;
	public List<KeeperBasic> keeper_basics;
	public Long current_keeper_id;
	public Client_Basic client_basic;
	// public coupon : null
	public Car car;
	public String pick_time;
	public String pick_time_segment;
	public String pick_start_time;
	public String pick_end_time;
	public String place_time;
	public String start_time;
	public String end_time;
	public List<Product> increase_products;
	public ClientFeedBack client_feedback;
//	public Integer keeper_rating;
//	public Integer order_rating;
	public String user_evaluation;
	public Double total_price;
	public Double fee;
	public Double coupon_price;
	public Operator operator;
	public List<Supply> suppliers;
	public String comment;
	public String product_comment;
	public String operator_comment;
	public Long take_keeper_id;
	public String take_time;
	public String give_back_start_time;
	public String give_back_time;
	public Long give_back_keeper_id;
	// public List<Long> relation_order_ids;
	public Boolean keeper_confirmed;
	// public pay_types;
	public Integer pay_type;
	// public pay_type_infos [1]
	// public sale_source : null
	public Integer pay_status;//1,已支付；2未支付；3部分支付
	public String pay_status_value;
	public Integer pay_mode;//1线上；2线下
	public String order_status_key;
	public String order_status_value;
	public String status;
	// public String sale_person;
	public Double paid_price;
	public Double not_paid_price;
	public String supplier_mold;//community  社区店

	protected Order(Parcel in) {
		number = in.readString();
		service_type = in.readString();
		peer_source = in.readString();
		pick_time = in.readString();
		pick_time_segment = in.readString();
		pick_start_time = in.readString();
		pick_end_time = in.readString();
		place_time = in.readString();
		start_time = in.readString();
		end_time = in.readString();
		user_evaluation = in.readString();
		comment = in.readString();
		product_comment = in.readString();
		operator_comment = in.readString();
		take_time = in.readString();
		give_back_start_time = in.readString();
		give_back_time = in.readString();
		pay_status_value = in.readString();
		order_status_key = in.readString();
		order_status_value = in.readString();
		status = in.readString();
		supplier_mold = in.readString();
	}

	public static final Creator<Order> CREATOR = new Creator<Order>() {
		@Override
		public Order createFromParcel(Parcel in) {
			return new Order(in);
		}

		@Override
		public Order[] newArray(int size) {
			return new Order[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(number);
		dest.writeString(service_type);
		dest.writeString(peer_source);
		dest.writeString(pick_time);
		dest.writeString(pick_time_segment);
		dest.writeString(pick_start_time);
		dest.writeString(pick_end_time);
		dest.writeString(place_time);
		dest.writeString(start_time);
		dest.writeString(end_time);
		dest.writeString(user_evaluation);
		dest.writeString(comment);
		dest.writeString(product_comment);
		dest.writeString(operator_comment);
		dest.writeString(take_time);
		dest.writeString(give_back_start_time);
		dest.writeString(give_back_time);
		dest.writeString(pay_status_value);
		dest.writeString(order_status_key);
		dest.writeString(order_status_value);
		dest.writeString(status);
		dest.writeString(supplier_mold);
	}
}
