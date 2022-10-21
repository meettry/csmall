package cn.tedu.csmall.commons.pojo.stock.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Meettry
 * @date 2022/10/21 14:21
 */
@Data
public class Stock implements Serializable {

    private Integer id;
    private String commodityCode;
    private Integer reduceCount;
}
