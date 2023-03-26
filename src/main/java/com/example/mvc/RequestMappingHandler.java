package com.example.mvc;

import com.example.mvc.controller.Controller;
import com.example.mvc.controller.FooController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler {
    private Map<String, Controller> mappings = new HashMap<>(); // key : urlPath, value : Controller

    void init() {
        mappings.put("/", new FooController()); // root 경로일떄, FooController가 실행
    }

    public Controller findHandler(String uriPath) {
        return mappings.get(uriPath);   // urlPath에 맞는 Controller를 반환하는 메서드
    }
}
