package com.example.springboot;

import com.example.springboot.contorller.HelloController;
import com.example.springboot.contorller.HelloServletController;
import com.example.springboot.service.SimpleHelloService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class SpringDispatcherServletApplication {

    public static void main(String[] args) {
        System.out.println("hello container less standard alone");

        // 스프링 컨테이너 생성
        // ApplicationContext : 애플리케이션을 구성하는 정보를 담고 있음, Bean, Resources 등등
        // ApplicationContext 가 결국 스프링 컨테이너가 된다
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext)


            )
            // 서블릿으로 들어오는 모든 요청 처리를 위해 와일드 카드
            .addMapping("/*");
        });

        // tomcat servlet container 동작
        webServer.start();
    }

}
