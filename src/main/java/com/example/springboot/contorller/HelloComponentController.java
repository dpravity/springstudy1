//package com.example.springboot.contorller;
//
//import com.example.springboot.service.HelloService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Objects;
//
//@RequestMapping(value = "/hello")
//@Component
//public class HelloComponentController {
//    private final HelloService helloService;
//
//    public HelloComponentController(HelloService helloService) {
//        this.helloService = helloService;
//    }
//
//    @GetMapping
//    @ResponseBody
//    public String hello(String name){
//        // DI 형태로 변경
////        SimpleHelloService helloService = new SimpleHelloService();
//
//        // Objects.requireNonNull : object 가 null 인 경우 throw NullPointerException
//        return helloService.sayHello(Objects.requireNonNull(name));
////        return "hello : " + name;
//    }
//
//}
