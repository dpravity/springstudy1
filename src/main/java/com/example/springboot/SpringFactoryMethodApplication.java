package com.example.springboot;

import com.example.springboot.contorller.HelloFactoryMethodController;
import com.example.springboot.service.HelloService;
import com.example.springboot.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Configuration 구성 정보를 가지고있는 클래스라는걸 명시
 * @Configuration + @Bean 을 붙인 자바 코드를 사용하려면 필요한 것
 * 1. GenericWebApplicationContext -> AnnotationConfigWebApplicationContext 으로 변경
 * 2. applicationContext.registerBean(HelloFactoryMethodController.class); -> applicationContext.register(HelloFactoryMethodController.class); 으로 변경
 */
@Configuration
//@ComponentScan(basePackages = "com.example.springboot.*")
//@ComponentScan
public class SpringFactoryMethodApplication {
    //#region - Bean 팩토리 메소드 형태로 Bean Object
    // 1번 방식 - Bean 팩토리 메소드
    @Bean
    public HelloFactoryMethodController helloController(HelloService helloService){
        return new HelloFactoryMethodController(helloService);
    }

    @Bean
    public HelloService helloService(){
        return new SimpleHelloService();
    }
    // 2번 방식 - 나를 Bean 으로 등록해줘!, component-scanner 가 @Component 가 붙은 모든 클래스를 Bean으로 등록
    // @Configuration + @ComponentScan

    //#endregion

    //#region - 2. 서블릿 컨테이너를 스프링 컨테이너 생성 중 초기화 되도록 변경(스프링에서 이렇게 구성되어 있음)
    public static void main(String[] args) {
        System.out.println("hello container less standard alone");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();  // 필수

                // Servlet Contrainer 생성 및 초기화
                ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
                WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
                    // 앞에서 구현했던 front controller Servlet 을 Spring 에서 지원하는 Dispatcher Servlet 으로 변환
//                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                            // 서블릿으로 들어오는 모든 요청 처리를 위해 와일드 카드..
                            .addMapping("/*");
                });

                // tomcat servlet container 동작
                webServer.start();
            }
        };

        applicationContext.register(SpringFactoryMethodApplication.class);
        // 스프링 컨테이너 초기화 작업, template method 패턴으로 구성되어 있음
        // 템플릿 메소드 안에서 일정한 순서에 의해 작업들이 호출되는데
        // 서브 클래스에서 확장하는 방법을 통해, 특정 시점에 어떤 작업을 수행하게 하여 기능을 유연하게 추가
        // hook method - onRefresh
        applicationContext.refresh();


    }
    //#endregion


    //#region - 1. 스프링 컨테이너 생성 및 초기화, 서블릿 컨테이너 생성 및 초기화
    /*
    public static void main2(String[] args) {
        System.out.println("hello container less standard alone");

        // 스프링 컨테이너 생성
        // ApplicationContext : 애플리케이션을 구성하는 정보를 담고 있음, Bean, Resources 등등
        // ApplicationContext 가 결국 스프링 컨테이너가 된다
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        // Servlet Contrainer 생성
        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            // 앞에서 구현했던 front controller Servlet 을 Spring 에서 지원하는 Dispatcher Servlet 으로 변환
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
            // 서블릿으로 들어오는 모든 요청 처리를 위해 와일드 카드..
            .addMapping("/*");
        });

        // tomcat servlet container 동작
        webServer.start();
    }
    */
    //#endregion

}
