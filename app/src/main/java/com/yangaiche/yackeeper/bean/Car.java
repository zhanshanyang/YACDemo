package com.yangaiche.yackeeper.bean;

import android.graphics.Picture;

import java.io.Serializable;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String KEY_ID = "id";
	public static final String KEY_DISABLED = "disabled";
	public static final String KEY_LICENCE = "licence";
	public static final String KEY_BOUGHT_TIME = "bought_time";
	public static final String KEY_MILES = "miles";
	public static final String KEY_CHASSIS_NUMBER = "chassis_number";
	public static final String KEY_ENGINE_NUMBER = "engine_number";
	public static final String KEY_BRAND = "brand";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_MODEL = "model";
	public static final String KEY_MODEL_TYPE = "model_type";
	public static final String KEY_BRAND_IMG_RUL = "brand_img_url";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_CARCARE_BOOK = "carcare_book";
	public static final String KEY_WHOLE_SCORE = "whole_score";
	public static final String KEY_PARTS = "parts";
	
	public Long id;
	public Boolean disabled;
	public Car_Licence licence;
	public String bought_time;
	public Integer miles;
	public String chassis_number;
	public String engine_number;
	public String brand;
	public String category;
	public String model;
	public Integer model_type;
	public Picture brand_img_url;
	public Long user_id;
	
	/*
      "car": {
            "id": 33,
            "disabled": true,
            "licence": {
                "province": "????",
                "number": "RB02225"
            },
            "brand": "宝马",
            "category": "X3",
            "model": "2014款 T L4 2.0T xDrive20i 领先型 ",
            "bought_time": "2011-08-21T18:02:52.000",
            "miles": 1244999,
            "chassis_number": "HIBIY797DFS7799111",
            "engine_number": "DFUSODFND567DFSDN-SDF9666",
            "brand_img_url": {
                "id": 1083,
                "thumbnail_url": "http://120.132.59.94:80/upload/carbrandlogo/1083.jpg",
                "original_url": "http://120.132.59.94:80/upload/carbrandlogo/1083.jpg"
            },
            "model_type": 1650,
            "user_id": 1002,
            "carcare_book": null,
            "whole_score": null,
            "parts": null
        },
	 */
	@Override
	public String toString() {
		return "Car [id=" + id + ", disabled=" + disabled + ", licence=" + licence + ", bought_time=" + bought_time + ", miles=" + miles + ", chassis_number=" + chassis_number + ", engine_number=" + engine_number + ", brand=" + brand + ", category=" + category + ", model=" + model + ", brand_img_url=" + brand_img_url + "]";
	}
	
	
}
