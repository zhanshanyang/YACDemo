package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String KEY_NAME = "name";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LONGTITUDE = "longitude";
	
	public String name ;
	public String address;
	public Double latitude;
	public Double longitude;
	
	public Location() {
		super();
	}

	public Location(String name, String address, Double latitude, Double longtitude) {
		super();
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longtitude;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", address=" + address + ", latitude=" + latitude + ", longtitude=" + longitude + "]";
	}
	
	/*
	 * "location":
             {
             "latitude":12333.1231231,
             "longtitude":1231231.1232,
             "name":"����ư�",
             "address":"����������·7��"
             }
	 */
	
}
