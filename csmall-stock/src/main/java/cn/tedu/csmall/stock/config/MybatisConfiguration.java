package cn.tedu.csmall.stock.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meettry
 * @date 2022/10/24 10:27
 */
@Slf4j
@Configuration
@MapperScan("cn.tedu.csmall.stock.mapper")
public class MybatisConfiguration {
    public MybatisConfiguration() {
        log.debug("stock模块正在创建Mybatis配置类");
    }
}
