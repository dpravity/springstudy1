package com.example.springboot.service;

import org.springframework.stereotype.Service;

//@Component
@Service
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }

}
