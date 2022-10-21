package cn.tedu.csmall.commons.pojo.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Meettry
 * @date 2022/10/21 11:42
 */
@ApiModel // 声明knife4j在线文档测试的注解
@Data
public class CartAddDTO implements Serializable {
    @ApiModelProperty(value = "商品编号",name = "commodityCode" , example = "PC100")
    private String commodityCode;
    @ApiModelProperty(value = "价格",name = "price" , example = "68")
    private Integer price;
    @ApiModelProperty(value = "数量",name = "count" , example = "3")
    private Integer count;
    @ApiModelProperty(value = "用户id",name = "userId" , example = "UU100")
    private String userId;
}
