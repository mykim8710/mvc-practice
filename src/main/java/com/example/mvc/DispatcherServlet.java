package com.example.mvc;

import com.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/") // 어떤 경로로 요청이 들어와 이 서블릿이 실행
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandler requestMappingHandler;

    @Override
    public void init() throws ServletException {
        requestMappingHandler = new RequestMappingHandler();
        requestMappingHandler.init();   // 초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("[DispatcherServlet] service started.");    // DispatcherServlet이 모든 요청을 받음

        try {
            Controller handler = requestMappingHandler.findHandler(request.getRequestURI());    // uri에 맞는 핸들러(Controller) 반환
            String viewName = handler.handleRequest(request, response); // ViewName 확인

            // 해당 ViewName으로 forward
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error("exception : {}", e.getMessage(), e);
        }
    }
}
