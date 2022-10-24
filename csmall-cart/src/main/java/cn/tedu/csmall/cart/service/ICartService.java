package cn.tedu.csmall.cart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;

/**
 * @author Meettry
 * @date 2022/10/24 10:53
 */
public interface ICartService {


    // 新增购物车商品的业务逻辑层方法
    void cartAdd(CartAddDTO cartAddDTO);

    //删除购物车商品中的业务逻辑层方法
    void deleteUserCart(String userId,String commodityCode);
}
