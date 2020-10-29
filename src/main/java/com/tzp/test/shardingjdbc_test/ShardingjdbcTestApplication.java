package com.tzp.test.shardingjdbc_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tzp.test.shardingjdbc_test.dao.mapper")
public class ShardingjdbcTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcTestApplication.class, args);
    }

}
