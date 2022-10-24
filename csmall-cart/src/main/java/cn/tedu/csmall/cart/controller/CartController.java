package cn.tedu.csmall.cart.controller;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meettry
 * @date 2022/10/24 11:31
 */
@RestController
@Api("购物车管理模块")
@RequestMapping("/base/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    @ApiOperation("新增购物车中商品")
    public JsonResult cartAdd(CartAddDTO cartAddDTO){
        cartService.cartAdd(cartAddDTO);
        return JsonResult.ok();
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID",name="userId",example = "UU100"),
            @ApiImplicitParam(value = "商品编号",name="commodityCode",example = "UU100"),
    })
    public JsonResult deleteUserCart(String userId,String commodityCode){
        cartService.deleteUserCart(userId,commodityCode);
        return  JsonResult.ok();
    }
}
