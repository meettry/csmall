package cn.tedu.csmall.stock.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Meettry
 * @date 2022/10/24 15:32
 */
@Repository
public interface StockMapper {

    // 新增减少指定商品编号库存的方法
    @Update("update stock_tbl set count=count-#{reduceCount}" +
            " where commodity_code = #{commodityCode} and count>=#{reduceCount}")
    int updateStockCount(@Param("commodityCode")String commodityCode,@Param("reduceCount")Integer reduceCount);
}
