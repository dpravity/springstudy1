package com.example.springboot;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

//@SpringBootApplication
public class SpringbootApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication.class, args);
//    }
    public static void main(String[] args) {
        System.out.println("hello container less standard alone");
        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        // servlet container 생성
        // 매개변수 ServletContextInitializer 를 이용하여 servlet 을 등록하는 작업을 수행
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            // 서블릿 context 에 servlet 추가
            servletContext.addServlet("hello", new HttpServlet() {
                // 웹 프로그래밍은 웹 요청을받아서 만들어짐
                // 요청 오브젝트, 응답 오브젝트
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    super.service(req, resp);
                    // 파라미터 받을 수 있도록 추가
                    String name = req.getParameter("name");

                    // 웹 응답 만들고 테스트
                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet : " + name);
                }
            })
            // 서블릿 매핑 해당 요청으로 들어오는 것은 해당 서블릿이 처리를 하겠다
            .addMapping("/hello");


        });
        // tomcat servlet container 동작
        webServer.start();
    }

}
