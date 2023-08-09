package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableScheduling
public class SkyApplication {
    public static void main(String[] args) {

    /*    springboot项目构建步骤:
            1.添加坐标
            <parent>
                <artifactId>spring-boot-starter-parent</artifactId>
                <groupId>org.springframework.boot</groupId>
                <version>2.7.3</version>
            </parent>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>

            2.编写业务代码

            3.编写引导类, springboot项目的入口
            @SpringBootApplication
            public class Application {
                public static void main(String[] args) {
                    SpringApplication.run(SkyApplication.class, args);
                }
            }
            内置tomcat

            */


        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
