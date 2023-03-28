package com.example.mvc;

import com.example.mvc.controller.Controller;
import com.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof Controller;   // 전달된 핸들러가 Controller 인터페이스의 구현체이면 true, 지원
    }

    @Override
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewPath = ((Controller) handler).handleRequest(request, response);
        return new ModelAndView(viewPath);
    }
}
