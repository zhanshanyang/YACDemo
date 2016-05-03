package com.yangaiche.yackeeper.bean;

import java.io.Serializable;
import java.util.List;

public class Inspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String KEY_ID = "id";
	public static final String KEY_CAR_INSPECTION_TYPE = "car_inspection_type";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_KEEPER_ID= "keeper_id";
	public static final String KEY_COMPLETE = "complete";
	public static final String KEY_ITEMS = "items";

	public Long id;
	/*
	 * surface:外观检测
	 * takeCar：12初检
	 * garage：全检
	 */
	public String car_inspection_type;
	public String description;
	public Long keeper_id;
	public Boolean complete;
	public List<InspectionItem> items;
	
	public Inspection(Long id, String car_inspection_type, String description, Long keeper_id, Boolean complete, List<InspectionItem> items) {
		super();
		this.id = id;
		this.car_inspection_type = car_inspection_type;
		this.description = description;
		this.keeper_id = keeper_id;
		this.complete = complete;
		this.items = items;
	}
	public Inspection() {
		super();
	}
	@Override
	public String toString() {
		return "Inspection [id=" + id + ", car_inspection_type=" + car_inspection_type + ", description=" + description + ", keeper_id=" + keeper_id + ",complete="+ complete + ", items=" + items + "]";
	}
	
}
