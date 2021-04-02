package com.unicom.timedtask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.unicom.timedtask.mapper")
@SpringBootApplication
@EnableScheduling
public class TimedTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimedTaskApplication.class, args);
    }

}
