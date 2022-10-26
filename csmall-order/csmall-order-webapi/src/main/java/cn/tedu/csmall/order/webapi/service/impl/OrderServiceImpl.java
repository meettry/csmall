package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/24 14:32
 */
@DubboService // order模块的方法会被business模块调用,所以也是生产者
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    // 添加@DubboReference注解,表示当前业务逻辑层中需要消费其他模块的服务
    // 声明的接口,应该是其他服务提供的业务逻辑层提供的接口
    // Nacos注册了业务的实现类,所以生命的接口会自动匹配到其实现类对象
    @DubboReference
    private IStockService stockService;

    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.减少订单所属商品的库存数(从stock模块中获取)
        // 先实例化业务逻辑层需要的指定类型的DTO对象才能调用
        StockReduceCountDTO stockReduceCountDTO = new StockReduceCountDTO();
        stockReduceCountDTO.setReduceCount(orderAddDTO.getCount());
        stockReduceCountDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        stockService.reduceCommodityCount(stockReduceCountDTO);
        log.debug("调用IStockService接口的方法,参数:{}", stockReduceCountDTO);
        // 2. 从购物车中删除用户勾选的商品(要调用cart模块的功能)
        cartService.deleteUserCart(orderAddDTO.getUserId(), orderAddDTO.getCommodityCode());
        log.debug("调用ICartService接口的deleteUserCart方法,参数1:{},参数2:{}", orderAddDTO.getUserId(), orderAddDTO.getCommodityCode());
        // 3.将orderAddDTO中的信息转换为Order实体类,然后以新增到数据库中

        Order order = new Order();
        BeanUtils.copyProperties(orderAddDTO, order);
        // 执行新增

        orderMapper.insertOrder(order);

        log.debug("新增订单信息为:{};", orderAddDTO);

    }
}
