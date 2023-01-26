package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Spring Container 란?
Business Objects(POJO) + Configuration Metadata 를 조합하여 내부의 Bean 이라고 불리는 오브젝트를 구성해서 서버 애플리케이션으로 만들어줌
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
