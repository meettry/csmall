package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/21 16:09
 */
@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {

    // Dubbo调用order模块的新增订单功能
    // 当前business是单纯地消费者.不需要在类上编写@DubboService
    @DubboReference
    private IOrderService dubboOrderService;
    @Override
    @GlobalTransactional  // seata给定的全局事务的注解,一旦写了这个注解,就相当于启动了分布式的事务,当前模块就是TM,所有远程调用操作数据库的功能都在同一个事务中
    public void buy() {
        // 模拟购买业务
        // 实例化一个用于新增订单的DTO对象
        OrderAddDTO orderAddDTO = new OrderAddDTO();
        // 为属性赋值
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(3);
        orderAddDTO.setMoney(500);
        // 模拟购买,无法操作数据库,所以先输出即可
        log.info("新增订单信息为:{}",orderAddDTO);

        // dubbo调用,将上面实例化的订单信息,生成为订单,影响数据库信息
        dubboOrderService.orderAdd(orderAddDTO);
    }
}
