package com.yangaiche.yackeeper.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CarKeeperOrder implements Parcelable {
	/**
	 * //添加接车提醒  还车提醒按钮
"place_time": "2015-04-28T01:40:24.000",	下单时间
"start_time": "2015-04-28T19:36:19.000",   管家确认时间
"pick_time": "2015-04-29 周三",		预约接车时间
"pick_time_segment": "09:00-10:00",
"take_time": "2015-04-29T15:18:41.000",		管家接车时间
"give_back_time": "2015-04-29T16:13:47.000",	管家还车时间
"end_time": null,	完成时间
	 */

	public Long id;
	public String number;
	public String service_Type;
	public String status;
	public String place_time;
	public String start_time;
	public String pick_time;
	public String pick_time_segment;
	public String take_time;
	public String give_back_time;
	public String end_time;
	public String peer_source;
	public Long car_id;
	public String customer_name;
	public String car_number;
	public String car_model;
	public String keeper;
	public String product_category;
	public String operator;
	public String pick_start_time;
	public String pick_end_time;

	protected CarKeeperOrder(Parcel in) {
		number = in.readString();
		service_Type = in.readString();
		status = in.readString();
		place_time = in.readString();
		start_time = in.readString();
		pick_time = in.readString();
		pick_time_segment = in.readString();
		take_time = in.readString();
		give_back_time = in.readString();
		end_time = in.readString();
		peer_source = in.readString();
		customer_name = in.readString();
		car_number = in.readString();
		car_model = in.readString();
		keeper = in.readString();
		product_category = in.readString();
		operator = in.readString();
		pick_start_time = in.readString();
		pick_end_time = in.readString();
	}

	public static final Creator<CarKeeperOrder> CREATOR = new Creator<CarKeeperOrder>() {
		@Override
		public CarKeeperOrder createFromParcel(Parcel in) {
			return new CarKeeperOrder(in);
		}

		@Override
		public CarKeeperOrder[] newArray(int size) {
			return new CarKeeperOrder[size];
		}
	};

	@Override
	public String toString() {
		return "CarKeeperOrder [id=" + id + ", number=" + number + ", service_Type=" + service_Type + ", status=" + status + ", place_time=" + place_time + ", start_time=" + start_time + ", pick_time=" + pick_time + ", pick_time_segment=" + pick_time_segment + ", take_time=" + take_time + ", give_back_time=" + give_back_time + ", end_time=" + end_time + ", peer_source=" + peer_source + ", car_id=" + car_id + ", customer_name=" + customer_name + ", car_number=" + car_number + ", car_model=" + car_model + ", keeper=" + keeper + ", product_category=" + product_category + ", operator=" + operator + "]";
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(number);
		dest.writeString(service_Type);
		dest.writeString(status);
		dest.writeString(place_time);
		dest.writeString(start_time);
		dest.writeString(pick_time);
		dest.writeString(pick_time_segment);
		dest.writeString(take_time);
		dest.writeString(give_back_time);
		dest.writeString(end_time);
		dest.writeString(peer_source);
		dest.writeString(customer_name);
		dest.writeString(car_number);
		dest.writeString(car_model);
		dest.writeString(keeper);
		dest.writeString(product_category);
		dest.writeString(operator);
		dest.writeString(pick_start_time);
		dest.writeString(pick_end_time);
	}
}
