package com.example.mvc.controller.v2;

import com.example.mvc.annotation.Controller;
import com.example.mvc.annotation.RequestMapping;
import com.example.mvc.enums.RequestHttpMethod;
import com.example.mvc.model.User;
import com.example.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
public class UserFormControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(UserFormControllerV2.class);

    //@RequestMapping(value = "/v2/users", method = RequestHttpMethod.POST)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("[POST] /v2/users");

        // user save business logic
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        log.info("userId = {}, name = {}", userId, name);

        User user = new User(userId, name);
        UserRepository.save(user);

        return "redirect:/v2/users";
    }
}
