package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meettry
 * @date 2022/10/21 16:17
 */
@RestController
@Slf4j
@RequestMapping("/base/business")
@Api(tags = "业务触发模块")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;


    // localhost:20000/base/business/buy
    // 因为代码设定的请求方式为Post,所以不能使用浏览器输入地址栏的方式测试,必须用Knife4J
    @PostMapping("/buy")
    @ApiOperation("执行业务的触发")
    @SentinelResource(value = "执行业务的触发",blockHandler = "blockError",fallback = "fallbackError")
    public JsonResult buy(){
        log.info("正在处理购买商品的请求");
        businessService.buy();
        log.info("处理购买商品请求成功");
        return JsonResult.ok();
    }

    public JsonResult blockError(BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,限流");
    }

    public JsonResult fallbackError(BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,降级");
    }
}
