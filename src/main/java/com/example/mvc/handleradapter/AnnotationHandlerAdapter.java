package com.example.mvc.handleradapter;

import com.example.mvc.AnnotationHandler;
import com.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return handler instanceof AnnotationHandler;
    }

    @Override
    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewPath = ((AnnotationHandler) handler).handlerRequest(request, response);
        return new ModelAndView(viewPath);
    }
}
