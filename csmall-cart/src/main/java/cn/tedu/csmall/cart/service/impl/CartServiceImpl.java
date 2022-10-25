package cn.tedu.csmall.cart.service.impl;

import cn.tedu.csmall.cart.mapper.CartMapper;
import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meettry
 * @date 2022/10/24 10:53
 */
@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        // 方法参数CartAddDTO,但是要执行mapper中给定的新增方法需要实体类Cart
        // 所以我们要将cartAddDTO对象中的属性值赋值为Cart实体类对象
        // 先实例化一个Cart实体类对象
        Cart cart=new Cart();
        // 使用BeanUtils类中给定的方法,将同名属性赋值
        BeanUtils.copyProperties(cartAddDTO,cart);
        // cart被赋值之后,就具备了调用mapper实现新增的条件了
        cartMapper.insertCart(cart);
        // 运行完成,输出日志信息
        log.info("新增购物车商品完成!:{}",cart);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        // 删除方法的参数是直接可以使用的,无需转换
        cartMapper.deleteCartByUserIdAndCommodityCode(userId,commodityCode);
        // 日志输出信息
        log.info("购物车删除完成");
    }
}
