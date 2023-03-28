package com.example.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationHandler {
    private final Class<?> clazz;
    private final Method targetMethod;

    public AnnotationHandler(Class<?> clazz, Method targetMethod) {
        this.clazz = clazz;
        this.targetMethod = targetMethod;
    }

    public String handlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();    // 클래스의 생성자
        Object handler = declaredConstructor.newInstance(); // 객체생성
        return (String)targetMethod.invoke(handler, request, response); // return viewPath
    }
}
