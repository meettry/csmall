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
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartAddDTO,cartAddDTO);
        int result = cartMapper.insertCart(cart);
        log.debug("新增购物车商品完成,受影响{},行",result);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        int result = cartMapper.deleteCartNyUserIdAndCommodityCode(userId, commodityCode);
        log.debug("购物车删除商品完成,受影响{},行",result);
    }
}
