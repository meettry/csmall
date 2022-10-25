package cn.tedu.csmall.stock.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo // 添加之后,在服务启动时,当前项目的服务才能正确注册到Nacos中
@SpringBootApplication
public class CsmallStockWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallStockWebapiApplication.class, args);
    }

}
