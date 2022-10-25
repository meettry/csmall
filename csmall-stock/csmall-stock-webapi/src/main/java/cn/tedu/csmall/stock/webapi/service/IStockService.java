package cn.tedu.csmall.stock.webapi.service;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;

/**
 * @author Meettry
 * @date 2022/10/24 15:57
 */
public interface IStockService {

    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);
}
