package com.example.mvc;

import com.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean support(Object handler);    // 전달된 핸들러의 지원여부 판단
    ModelAndView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception; // 핸들러를 실행하고 ModelAndView 객체를 반환환다.
}
