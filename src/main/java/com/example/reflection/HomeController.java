package com.example.reflection;

import com.example.WebApplicationServer;
import com.example.reflection.annotation.Controller;
import com.example.reflection.annotation.RequestMapping;
import com.example.reflection.enums.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET ]/home");
        return "home";
    }
}
