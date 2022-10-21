package cn.tedu.csmall.commons.pojo.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Meettry
 * @date 2022/10/21 14:20
 */
@Data
public class Order implements Serializable {

    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
