package cn.tedu.csmall.business.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/21 16:09
 */
@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {
    @Override
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

    }
}
