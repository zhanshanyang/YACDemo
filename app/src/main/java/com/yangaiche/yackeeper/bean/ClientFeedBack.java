package com.yangaiche.yackeeper.bean;

import java.io.Serializable;

public class ClientFeedBack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Boolean if_feedback_committed;
	public String comment;
	public Integer keeper_stars;
	public Integer order_stars;
/*
 * "client_feedback": {
            "if_feedback_committed": false,
            "comment": "",
            "keeper_stars": 5,
            "order_stars": 5
        },
 */
}
