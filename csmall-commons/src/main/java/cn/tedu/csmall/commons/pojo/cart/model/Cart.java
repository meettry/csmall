package cn.tedu.csmall.commons.pojo.cart.model;

import lombok.Data;

/**
 * @author Meettry
 * @date 2022/10/21 11:55
 */
@Data
public class Cart {
    private Integer id;
    private String commodityCode;
    private Integer price;
    private Integer count;
    private String userId;
}
