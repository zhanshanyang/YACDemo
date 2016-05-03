package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class KeeperBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long id;
	public String type;
	public String name;
	public String gender;
	public Double star_count;
	public Double rating;
	public String ID_number;
	public String phone_number;
	public Integer car_exp_year;
	public String avatar_img;
	public Boolean current;

/**
 * {
                "id": 7,
                "type": "keeper",
                "name": "高志军",
                "gender": "male",
                "star_count": 4,
                "rating": 4,
                "ID_number": "37292919710218391X",
                "phone_number": "15210752801",
                "car_exp_year": 1,
                "avatar_img": "http://7xiqd8.com2.z0.glb.qiniucdn.com/gaozhijun.jpg/s1024.jpg",
                "current": true
            }
 */

}
