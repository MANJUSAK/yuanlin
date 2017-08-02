package com.goodsoft.yuanlin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot程序主入口
 * 没有该类项目无法启动
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.goodsoft.landscape.repository")
@ComponentScan(basePackages = "com.goodsoft.yuanlin") // 开启通用注解扫描
@ServletComponentScan(basePackages = "com.goodsoft.yuanlin.config") // 扫描使用注解方式的servlet
public class YuanlinApplication {
    private static final Logger logger = LoggerFactory.getLogger(YuanlinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(YuanlinApplication.class, args);
    }
}
