package cn.tedu.csmall.stock.webapi.service.impl;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.stock.service.IStockService;
import cn.tedu.csmall.stock.webapi.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/24 15:58
 */
@DubboService
@Service
@Slf4j
public class StockServiceImpl implements IStockService {
    @Autowired
    StockMapper stockMapper;


    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        // 调用减少库存的业务逻辑层方法
        //  updateStockCount([商品编号],[减少的库存数])
        int row = stockMapper.updateStockCount(
                stockReduceCountDTO.getCommodityCode(),
                stockReduceCountDTO.getReduceCount());
        // 可以判断row是否为0\1输出库存修改成功或失败的信息
        log.info("库存减少完成");
    }
}
