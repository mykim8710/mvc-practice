package com.example.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /");

        // 비지니스 로직....

        return "main";  // return ViewName
    }
}
