package com.yangaiche.yackeeper.bean;

import android.graphics.Picture;

import java.io.Serializable;
import java.util.List;

public class InspectionItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_RESULT = "result";//this inspection is or qualified. true:合格 false:不合格
	public static final String KEY_PIC_ID = "pic_id";
	public static final String KEY_PICS = "pics";
	public static final String KEY_TRACK = "track";
	
	public Long id;
	public String name;
	public String comment;
	public Boolean result;//true 合格  false 不合格
	public Long[] pic_id;
	public List<Picture> pics;
	/**
	 * for the item's label.
	 */
	public Integer track;
	/**
	 * or is checked?
	 * true:server has this item. 
	 */
//	public boolean flag;
	
	public InspectionItem() {
		super();
	}
	
	public InspectionItem(String name, Integer track/*, boolean flag*/) {
		super();
		this.name = name;
		this.track = track;
//		this.flag = flag;
	}

	public InspectionItem(Long id, String name, String comment, Boolean result, Long[] pic_id, List<Picture> pics, Integer track) {
		super();
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.result = result;
		this.pic_id = pic_id;
		this.pics = pics;
		this.track = track;
	}

	@Override
	public String toString() {
		return "InspectionItem [id=" + id + ", name=" + name + ", comment=" + comment + ", result=" + result + ", pic_id=" + pic_id + ",track="+track+"]";
	}
	
}
