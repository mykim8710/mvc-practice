package com.example.mvc.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 단순 View를 리턴하는 핸들러(컨트롤러)
 */
public class ForwardController implements  Controller{
    private static final Logger log = LoggerFactory.getLogger(UserFormController.class);
    private final String forwardViewPath;

    public ForwardController(String forwardViewPath) {
        this.forwardViewPath = forwardViewPath;
    }
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Forward Controller");
        return forwardViewPath;
    }
}
