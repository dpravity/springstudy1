package com.example.springboot;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.contorller.HelloServletController;
import com.example.springboot.contorller.HelloServletOriginController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

//@SpringBootApplication
public class SpringServletOriginApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication.class, args);
//    }
    public static void main(String[] args) {
        System.out.println("hello container less standard alone");
        ServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        /*
        1. 클라이언트 요청사항이 각 서블릿에 직접 매핑이 되면 공통적으로 처리하는 것이 문제, 기본 서블릿 기능으로는 한계가 있음
        2. 그래서 나온 것이 Front Controller : 모든 서블릿에 공통적으로 들어오는 중앙 관리가 생김
        3. Front Controller -> handler 요청의 종류에 따라 로직을 처리하는 다른 오브젝트에 위임하여 전달
        4. Front Controller 가 처리해주는 공통 작업 : 다국어, 인증, 보안, 모든 웹 요청에 대해 공통적으로 리턴해야하는 것들
         */

        //--------------- 독립 실행이 가능한 서블릿 애플리케이션 코드 ---------------
        // 스프링 기능을 사용하지 않고 서블릿 기능만 사용, main 메소드에서 servlet -> controller
        // servlet container 생성
        // 모든 서블릿의 공통 처리를 위한 front controller 생성
        WebServer webServer = servletWebServerFactory.getWebServer(servletContext -> {
            // 서블릿 초기화하는 부분에
            // front controller 에서 위임할 hellohandler 또는 hellocontroller 추가
            HelloServletOriginController helloController = new HelloServletOriginController();

            // 서블릿 context 에 servlet 추가
            // 모든 요청을 받아 공통처리를 할 front controller
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 매핑과 바인딩
                    // 매핑 : 웹 요청에 들어있는 정보를 활용하여 어떤 로직을 수행하는 코드를 실행할 것인가
                    // 바인딩 : 웹 요청과 응답을 다루는 오브젝트를 사용하지 않음, 웹 요청 정보를 변환하여 사용
                    // 웹 요청을 가지고 이것을 처리하는 로직 코드에서 사용할 수 있도록 새로운 형태의 타입으로 변환을 해주는 것
                    // * MVC 바인딩은 더많은 처리 과정이 있음

                    // 다국어, 인증, 보안, 모든 웹 요청에 대한 공통 기능
                    // HTTP request 3가지 요소
                    if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
                        String name = req.getParameter("name");

                        // front controller -> hello controller 응답 값
                        String result = helloController.hello(name);

                        // 서블릿 컨테이너가 에러가 나지않는 이상 서블릿 상태 코드 생략 가능
                        // resp.setStatus(HttpStatus.OK.value());
                        // Header Content_Type 을 추가하는 다른 방식
                        // resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.setHeader("Content-Type", "text/plain");
                        // hello controller 응답의 값을 웹 응답의 바디에 할당
                        // resp.getWriter().println("Hello Servlet : " + name);
                        resp.getWriter().println(result);
                    }else if(req.getRequestURI().endsWith("/user")){
                        // TODO
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }else{
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            })
            // 서블릿으로 들어오는 모든 요청 처리를 위해 와일드 카드
            .addMapping("/*");
        });

        //#region - hello servlet
        /*
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
        */
        //#endregion - hello servlet


        // tomcat servlet container 동작
        webServer.start();
    }

}
