package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meettry
 * @date 2022/10/24 15:59
 */
@RestController
@RequestMapping("/base/stock")
@Slf4j
public class StockController {

    @Autowired
    private IStockService stockService;

    // 该注解标记为控制层上,在该方法运行后,会被Sentinel检测,
    // 该方法运行前Sentinel时,监测不到,必须至少运行一次后才可以开始监测信息
    // 该注解括号里引号内的内容,默认是该注解的value值
    // blockHandler是指定被限流时,要运行自定义方法的属性,它的值为指定的方法的方法名
    // fallback是指当控制器发生异常时,会运行自定义方法的属性,它的值为方法的方法名
    @SentinelResource(value = "减少库存数", blockHandler ="blockError",fallback = "fallbackError")
    @PostMapping("/reduce/count")
    @ApiOperation("减少商品库存数")
    public JsonResult reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        stockService.reduceCommodityCount(stockReduceCountDTO);
        // 随机发生异常,测试服务降级效果
//        if(Math.random()<0.5){
//            // 如果发生这个异常,就会运行降级方法
//            throw new CoolSharkServiceException(
//                    ResponseCode.INTERNAL_SERVER_ERROR, "随机异常");
//        }
        return JsonResult.ok();
    }

    // Sentinel自定义限流方法,在被其限流时,不执行注解标注的方法,而运行此方法
    // 规则:
    // 1. 访问修饰符必须是public
    // 2. 返回值类型必须和控制器方法一致
    // 3. 方法名必须是控制器方法注解中的blockHandler定义的名称
    // 4. 方法的参数列表必须包含控制器的所有参数,而且可以额外添加BlockException异常参数
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO, BlockException e){
        // 运行这个方法表示被限流了,需要给出被限流的提示
        log.debug("reduceCommodityCount方法被限流了,参数:{}",stockReduceCountDTO);
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,请稍后再试");
    }

    // Sentinel自定义降级方法,当控制器方法发生异常时,Sentinel会调用这个方法,优先级比统一异常处理类高
    // 规则:与限流方法基本一致,只有参数异常为Throwable
    // 应用场景: 当业务版本更新时,为了防止新版本出现未知bug,可以让新版本降级为老版本
    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,Throwable throwable){
        // 运行这个方法表示被降级了,需要给出被降级的提示
        log.debug("reduceCommodityCount方法被降级了,参数:{}",stockReduceCountDTO);
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"运行发生异常,服务降级");
    }

}
