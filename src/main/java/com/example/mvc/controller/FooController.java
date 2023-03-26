package com.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FooController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "foo.jsp";   // FooController가 호출되면 foo라는 이름의 화면을 리턴
    }
}
