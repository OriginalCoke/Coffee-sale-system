package com.hwy.proj_425;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.hwy.proj_425.mapper")
@SpringBootApplication
public class Proj425Application {

    public static void main(String[] args) {
        SpringApplication.run(Proj425Application.class, args);
    }
}
