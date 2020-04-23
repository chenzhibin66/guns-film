package com.stylefeng.guns.rest;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
@MapperScan("com.stylefeng.guns.rest.modular.film.dao")
@EnableDubbo
public class CinemaApplication {

    public static void main(String[] args) {

        SpringApplication.run(CinemaApplication.class, args);
    }
}
