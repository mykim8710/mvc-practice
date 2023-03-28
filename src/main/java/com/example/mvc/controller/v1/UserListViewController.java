package com.example.mvc.controller.v1;

import com.example.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListViewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserListViewController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /v1/users");

        request.setAttribute("users", UserRepository.findAll());

        //ModelAndView modelAndView = new ModelAndView("/user/list", Map.of("users", UserRepository.findAll()));

        return "/userV1/list";
    }
}
