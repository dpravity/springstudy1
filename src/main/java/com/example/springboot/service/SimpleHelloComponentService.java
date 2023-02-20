package com.example.springboot.service;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloComponentService implements HelloService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }

}
