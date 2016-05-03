package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class Supply implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_RATING = "rating";
	public static final String KEY_LAYOFF = "layoff";
	public static final String KEY_CONTACT_NAME = "contact_name";
	public static final String KEY_PHONE_NUMBER = "phone_number";
	public static final String KEY_MOBILE_NUMBER = "mobile_number";
	public static final String KEY_TIME_SCORE = "time_score";
	public static final String KEY_PRICE_SCORE = "price_score";
	public static final String KEY_SERVICE_SCORE = "service_score";
	public static final String KEY_PART_SCORE = "part_score";
	public static final String KEY_WORK_SCORE = "work_score";
	public static final String KEY_CONFIRM_SCORE = "confirm_score";
	public static final String KEY_ORDER_SUPPLIER_RATING = "order_supplier_rating";
	public static final String KEY_ARRIVED = "arrived";
	public static final String KEY_SUPPLIER_ID = "supplier_id";
	public static final String KEY_ORDER_ID = "order_id";
	public static final String KEY_ARRIVED_KEEPER_ID = "arrived_keeper_id";
	public static final String KEY_TYPE = "type";
	public static final String KEY_EVALUATION = "evaluation";
	public static final String KEY_EVALUATION_KEEPER_ID = "evaluated_keeper_id";
	
	public Long id;
	public String name;
	public Double longitude;
	public Double latitude;
	public String address;
	public Double rating;
	public Boolean layoff;
	public String contact_name;
	public String phone_number;
	public String mobile_number;
	public Integer time_score;
	public Integer price_score;
	public Integer service_score;
	public Integer part_score;
	public Integer work_score;
	public Integer confirm_score;
	public Double order_supplier_rating;
	public Boolean arrived;
	public Long supplier_id;
	public Long order_id;
	public Long arrived_keeper_id;
	public String type;
	public String evaluation;
	public Long evaluated_keeper_id;
	
}
