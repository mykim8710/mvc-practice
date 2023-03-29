package com.example.di.controller;

import com.example.di.annotation.CustomInject;
import com.example.di.service.FooService;

@com.example.di.annotation.CustomController
public class FooController {
    private final FooService fooService;

    @CustomInject
    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    // di 프레임워크가 정상적으로 동작한다면 userService가 null이 아닐 것이다.
    public FooService getFooService() {
        return fooService;
    }
}
