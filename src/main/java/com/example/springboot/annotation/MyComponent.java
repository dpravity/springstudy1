package com.example.springboot.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Component 특징 : 메타 어노테이션(어노테이션 위에 붙은 어노테이션) 위에도 붙일 수 있다
// Bean Object 등록 시 어떤 역할을 하는 컴포넌트인지 명시할 수 있음 
@Component
public @interface MyComponent {
}
