package cn.tedu.csmall.commons.pojo.stock.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Meettry
 * @date 2022/10/21 14:21
 */
@ApiModel(value = "商品减库存DTO")
@Data
public class StockReduceCountDTO implements Serializable {

    @ApiModelProperty(value = "商品编号",name="commodityCode",example = "PC100")
    private String commodityCode;
    @ApiModelProperty(value = "减库存数",name="reduceCount",example = "5")
    private Integer reduceCount;
}
