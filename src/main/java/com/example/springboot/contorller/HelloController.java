package com.example.springboot.contorller;

import com.example.springboot.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Dispatcher servlet 읽기 순서
 * 1. 클래스 레벨
 * @ReestController
 * 2. 메소드 레벨
 * @GetMapping
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
