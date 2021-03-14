package com.deepglint.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-15 23:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private int userId;
    private String telephone;
    private String appName;
    private String password;
    private Date createTime;
    private Date modifyTime;


}
