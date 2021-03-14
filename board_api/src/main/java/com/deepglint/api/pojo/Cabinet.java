package com.deepglint.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Cabinet
 * @Description 储物柜
 * @author: cdf
 * @Date: 2021-02-17 15:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabinet implements Serializable {

    private int cabinetId;
    private int using;
    private Date startTime;
    private Date stopTime;

}
