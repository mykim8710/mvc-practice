package com.example.servlet;

import com.example.calculator.domain.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculateV3")
public class CalculatorServletV3 extends HttpServlet {   // service() 메서드만 구현하면 된다.
    private static final Logger log = LoggerFactory.getLogger(CalculatorServletV3.class);

    // get 요청
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("call doGet()");

        // get Parameter from Request
        String operand1 = request.getParameter("operand1");
        String operand2 = request.getParameter("operand2");
        String operator = request.getParameter("operator");

        System.out.println("operator = " + operator);
        System.out.println("operand1 = " + operand1);
        System.out.println("operand2 = " + operand2);

        // 비지니스 로직
        int calculatorResult = Calculator.calculator(Integer.parseInt(operand1), operator, Integer.parseInt(operand2));
        System.out.println("calculatorResult = " + calculatorResult);

        // set Response
        PrintWriter writer = response.getWriter();
        writer.println(calculatorResult);
    }

    // doPost() : post 요청 시 재정의
    // doPut() : put 요청 시 재정의
    // doDelete() : delete 요청 시 재정의
}
