package com.example.mvc.controller.v2;

import com.example.mvc.annotation.Controller;
import com.example.mvc.annotation.RequestMapping;
import com.example.mvc.enums.RequestHttpMethod;
import com.example.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
public class UserListViewControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(UserListViewControllerV2.class);

    //@RequestMapping(value = "/v2/users", method = RequestHttpMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /v2/users");
        request.setAttribute("users", UserRepository.findAll());
        return "/userV2/list";
    }
}
