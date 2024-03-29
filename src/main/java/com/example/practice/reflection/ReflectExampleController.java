package com.example.practice.reflection;

import com.example.WebApplicationServer;
import com.example.practice.reflection.annotation.ReflectController;
import com.example.practice.reflection.annotation.ReflectRequestMapping;
import com.example.practice.reflection.enums.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ReflectController
public class ReflectExampleController {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    @ReflectRequestMapping(value = "/reflection", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /reflection");
        return "reflection";
    }
}
