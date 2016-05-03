package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class Car_Licence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY_PROVINCE = "province";
	public static final String KEY_NUMBER = "number";
	
	public String province;
	public String number;
	@Override
	public String toString() {
		return "Car_Licence [province=" + province + ", number=" + number + "]";
	}
	public Car_Licence() {
		super();
	}
	public Car_Licence(String province, String number) {
		super();
		this.province = province;
		this.number = number;
	}

	/*
	 * "licence": {
          "province": "��",
          "number": "RB0718"
        },
	 */
}
