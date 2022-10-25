package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/24 14:32
 */
@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.减少订单所属商品的库存数(从stock模块中获取)

        // 2. 从购物车中删除用户勾选的商品(要调用cart模块的功能)

        // 3.将orderAddDTO中的信息转换为Order实体类,然后以新增到数据库中
        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        // 执行新增

        orderMapper.insertOrder(order);


        log.debug("新增订单信息为:{};",orderAddDTO);

    }
}
