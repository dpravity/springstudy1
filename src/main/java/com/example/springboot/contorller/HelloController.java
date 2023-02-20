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
// bean object 로 만듬
// @Component 특징 : 메타 어노테이션(어노테이션 위에 붙은 어노테이션) 위에도 붙일 수 있다
//@Component
// 메타 어노테이션으로 컴포넌트가 존재한다면 bean 으로 등록해준다
//@MyComponent
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping("/hello")
//    @ResponseBody
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
