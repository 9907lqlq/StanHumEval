package com.ping.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ping.system.mapper")
@SpringBootApplication
public class QuestionnaireSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireSystemApplication.class);
    }
    
}
