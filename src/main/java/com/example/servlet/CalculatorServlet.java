package com.example.servlet;

import com.example.WebApplicationServer;
import com.example.calculator.domain.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")   // 해당 path로 요청이 되면 CalculatorServlet이 실행된다.
public class CalculatorServlet implements Servlet {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    private ServletConfig servletConfig;

    // init() : 서블릿 컨테이너가 서블릿 생성 후 초기화 작업을 수행하기 위해 호출하는 메서드
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("call init()");    // 최초 한번만 호출

        // 리소스 초기화
        this.servletConfig = servletConfig;
    }

    // service() : 클라이언트 요청이 들어올때마다 서블릿 컨테이너가 호출하는 메서드
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("call service()");

        // get Parameter from Request
        String operand1 = request.getParameter("operand1");
        String operand2 = request.getParameter("operand2");
        String operator = request.getParameter("operator");

        System.out.println("operator = " + operator);
        System.out.println("operand1 = " + operand1);
        System.out.println("operand2 = " + operand2);

        // 비지니스 로직
        int calculatorResult = Calculator.calculator(Integer.parseInt(operand1), operator, Integer.parseInt(operand2));

        // set Response
        PrintWriter writer = response.getWriter();
        writer.println(calculatorResult);
    }

    // destroy() : 서블릿 컨테이너가 종료될때 호출되는 메서드
    @Override
    public void destroy() {
        log.info("call destroy()");
        // 할당된 자원의 해제..
    }

    // 기타 메서드
    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

}
