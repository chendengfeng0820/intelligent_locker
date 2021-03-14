package com.deepglint.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Courier
 * @Description TODO
 * @author: cdf
 * @Date: 2021-03-06 18:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier implements Serializable {

	private int courierId;
	private String courierTelephone;
	private String realName;
	private String password;
	private String identityId;
	private Date createTime;
	private Date modifyTime;
}
