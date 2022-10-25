package cn.tedu.csmall.order.service;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;

/**
 * @author Meettry
 * @date 2022/10/24 14:32
 */
public interface IOrderService {

    // 声明新增订单的业务逻辑层的方法
    void orderAdd(OrderAddDTO orderAddDTO);
}
