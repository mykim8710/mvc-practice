package com.example.mvc.controller;

import com.example.mvc.repository.UserRepository;
import com.example.mvc.view.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class UserListViewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserListViewController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /users");


        request.setAttribute("users", UserRepository.findAll());

        //ModelAndView modelAndView = new ModelAndView("/user/list", Map.of("users", UserRepository.findAll()));

        return "/user/list";
    }
}
