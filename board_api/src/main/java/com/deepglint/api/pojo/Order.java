package com.deepglint.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单
 * @author: cdf
 * @Date: 2021-02-18 11:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	private long orderId;
	private int from;
	private int to;
	private Date expectTime;
	private Date orderTime;
	private int status;
}
