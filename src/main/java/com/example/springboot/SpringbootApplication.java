package com.example.springboot;

import com.example.springboot.contorller.HelloController;
import com.example.springboot.service.SimpleHelloService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
Spring Container 란?
Business Objects(POJO) + Configuration Metadata 를 조합하여 내부의 Bean 이라고 불리는 오브젝트를 구성해서 서버 애플리케이션으로 만들어줌
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        System.out.println("hello container less standard alone");

        // 스프링 컨테이너 생성
        // ApplicationContext : 애플리케이션을 구성하는 정보를 담고 있음, Bean, Resources 등등
        // ApplicationContext 가 결국 스프링 컨테이너가 된다
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(){
            // 스프링 컨테이너 초기화
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 컨테이너 초기화 중 dispatcher servlet 등록
                ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
                WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*"); // 서블릿으로 들어오는 모든 요청 처리를 위해 와일드 카드
                });
                // tomcat servlet container 동작
                webServer.start();
            }
        };
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        // 스프링 컨테이너 초기화, 템플릿 메소드 패턴(상속을 통해 기능 확장)
        applicationContext.refresh();
    }

}
