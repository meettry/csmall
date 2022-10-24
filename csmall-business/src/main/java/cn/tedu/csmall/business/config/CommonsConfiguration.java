package cn.tedu.csmall.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meettry
 * @date 2022/10/21 15:34
 */
@Slf4j
// 只有添加了@Configuration注解的类才能配置当前Spring环境
@Configuration
// 要扫描commons模块中的统一异常处理类所在的路径,否则异常处理的功能不会生效
@ComponentScan("cn.tedu.csmall.commons.exception")
public class CommonsConfiguration {

}
