package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFormController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserFormController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[POST] /users");

        // user save business logic
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        log.info("userId = {}, name = {}", userId, name);

        User user = new User(userId, name);
        UserRepository.save(user);

        return "redirect:/users";
    }
}
