package cn.tedu.csmall.cart.mapper;

import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Meettry
 * @date 2022/10/24 10:36
 */
@Repository
public interface CartMapper {

    // 新增购物车的方法
    @Insert("insert into cart_tbl(commodity_code,price,count,user_id) values(#{commodityCode},#{price},#{count},#{userId})")
    int insertCart(Cart cart);

    // 删除购物车
    @Delete("delete from cart_tbl where user_id=#{userId} and commodity_code=#{commodityCode}")
    int deleteCartNyUserIdAndCommodityCode(@Param("userId")String userId,@Param("commodityCode")String commodityCode);
}
