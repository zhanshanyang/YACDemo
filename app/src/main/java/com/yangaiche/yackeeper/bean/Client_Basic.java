package com.yangaiche.yackeeper.bean;


public class Client_Basic {
	public static final String KEY_ID = "id";
	public static final String KEY_PHONE_NUMBER = "phone_number";
	public static final String KEY_NAME = "name";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_AVATAR_IMG = "avatar_img";
	public static final String KEY_LOCATION = "location";
	
	public Long id;
	public String phone_number;
	public String name;
	public String gender;
	public String avatar_img;
	public Location location;
	@Override
	public String toString() {
		return "Client_Basic [id=" + id + ", phone_number=" + phone_number + ", name=" + name + ", gender=" + gender + ", avatar_img=" + avatar_img + ", location=" + location + "]";
	}
	
	/*
	 * "client_basic": {
        "id": 1233333,
        "phone_number": "15010111335",
        "name": "������",
        "gender": "male",
        "avatar_img": "http://www.baidu.com/a.png",
        "location":
             {
             "latitude":12333.1231231,
             "longtitude":1231231.1232,
             "name":"����ư�",
             "address":"����������·7��"
             }
      },
	 */
	
}
