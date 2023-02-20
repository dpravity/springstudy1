package com.example.springboot.contorller;

import com.example.springboot.entity.Hello;
import com.example.springboot.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
/*

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
//        return "hello : " + name;
    }

    @PostMapping("/hellopost2")
    public String hellopost(@RequestBody String name){
        return "hello : " + name;
    }
    @PostMapping("/hellopost")
    public String hellopost(@RequestBody Hello hello){
        return "hello : " + hello.getName();
    }
}
*/
