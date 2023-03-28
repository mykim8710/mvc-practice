package com.example.mvc.controller.v2;

import com.example.mvc.annotation.Controller;
import com.example.mvc.annotation.RequestMapping;
import com.example.mvc.enums.RequestHttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
public class UserFormViewControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(UserFormViewControllerV2.class);

    //@RequestMapping(value = "/v2/users/form", method = RequestHttpMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /v2/users/form");
        return "/userV2/form";
    }
}
