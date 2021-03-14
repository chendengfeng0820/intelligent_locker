package com.deepglint.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Baggage
 * @Description 行李类
 * @author: cdf
 * @Date: 2021-02-17 16:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Baggage implements Serializable {

    private int baggageId;
    private Date startTime;
    private Date stopTime;
    private int status;

}
