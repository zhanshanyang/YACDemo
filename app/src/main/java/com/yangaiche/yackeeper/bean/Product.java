package com.yangaiche.yackeeper.bean;

import android.graphics.Picture;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int PICKCAR_TYPE = 11;
	public static final String KEY_ID = "id";
	public static final String KEY_PRODUCT_TYPE = "product_type";
	public static final String KEY_PRODUCT_NAME = "product_name";
	public static final String KEY_UNIT_COUNT = "unit_count";
	public static final String KEY_PRICE = "price";
	public static final String KEY_LABOUR_PRICE = "labour_price";
	public static final String KEY_TOTAL_PRICE = "total_price";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_SOURCE_INSPECTION = "source_inspection";
	public static final String KEY_COMPLETE = "complete";
	public static final String KEY_PIC_ID = "pic_id";
	public static final String KEY_PICS = "pics";
	public static final String KEY_PART_TYPE = "part_type";
	public static final String KEY_KEEPER_ID = "keeper_id";
	
	public Long id;
	public Boolean disabled;
	public Long product_type;
	public String product_name;
	public Integer unit_count;
	public Double price;
	public Double labour_price;
	public Double total_price;
	public String comment;
	public String source_inspection;
	public Boolean complete;
	public Long[] pic_id;
	public List<Picture> pics;
	public Long part_type;
	public Long keeper_id;
	public String complete_time;
	public Boolean user_defined;
	public String product_info;
	public List<String> product_categories;
	public Long referee_keeper_id;
	public Long referee_operator_id;
	public Integer selection_mode;//1,用户自主下单;2,待确认;3,同意;4,拒绝
	public Integer pay_status;//0，未付款；1，已付款
	public Boolean required_pay;
	public Integer source_mode;
	
	public Product() {
		super();
	}
	
	public Product(long id, String product_name) {
		super();
		this.id = id;
		this.product_name = product_name;
	}
	
	public Product(long id, String product_name, Boolean complete) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.complete = complete;
	}
	
}
