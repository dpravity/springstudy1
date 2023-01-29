package com.example.springboot.contorller;

import com.example.springboot.service.HelloService;

import java.util.Objects;

public class HelloServletController {
    private final HelloService helloService;

    public HelloServletController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name){
        // DI 형태로 변경
//        SimpleHelloService helloService = new SimpleHelloService();

        // Objects.requireNonNull : object 가 null 인 경우 throw NullPointerException
        return helloService.sayHello(Objects.requireNonNull(name));
//        return "hello : " + name;
    }

}
