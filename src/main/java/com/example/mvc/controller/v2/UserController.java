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

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/v2/users", method = RequestHttpMethod.GET)
    public String userListView(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /v2/users");
        request.setAttribute("users", UserRepository.findAll());
        return "/userV2/list";
    }

    @RequestMapping(value = "/v2/users/form", method = RequestHttpMethod.GET)
    public String UserFormView(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /v2/users/form");
        return "/userV2/form";
    }

    @RequestMapping(value = "/v2/users", method = RequestHttpMethod.POST)
    public String userForm(HttpServletRequest request, HttpServletResponse response) {
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
