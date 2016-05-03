package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class Operator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_TYPE = "type";
	public static final String KEY_AVATAR_IMG = "avatar_img";
	public static final String KEY_PHONE_NUMBER = "phone_number";
	
	public Long id;
	public String name;
	public String gender;
	public String type;
	public String avatar_img;
	public String phone_number;
	/*
	 * "operator": {
"id": 1006,
"name": "王文燃",
"gender": "male",
"type": "operator",
"avatar_img": "",
"phone_number": "15202207925"
},
	 */
}
