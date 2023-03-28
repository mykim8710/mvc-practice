package com.example.mvc;

import com.example.mvc.controller.*;
import com.example.mvc.enums.RequestHttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 경로(uriPath)에 따른 핸들러(controller)를 가지고 있음
 * DispatcherServlet으로 부터 경로(uriPath)에 따른 핸들러(controller)를 리턴해줌
 */

public class RequestHandlerMapping {
    private Map<HandlerKey, Controller> mappings = new HashMap<>(); // key : urlPath, value : Handler(controller)

    void init() {
        mappings.put(new HandlerKey("/", RequestHttpMethod.GET), new MainController());
        mappings.put(new HandlerKey("/users", RequestHttpMethod.GET), new UserListViewController());
        mappings.put(new HandlerKey("/users/form", RequestHttpMethod.GET), new FowardController("/user/form"));
        mappings.put(new HandlerKey("/users/edit", RequestHttpMethod.GET), new FowardController("/user/edit"));
        mappings.put(new HandlerKey("/users", RequestHttpMethod.POST), new UserFormController());
    }

    // handlerKey에 맞는 Controller를 반환하는 메서드
    /**
     * handlerKey = {
     *                  "uriPath" : "/users",
     *                  "requestMethod" : "GET"
     *              }
     */
    public Controller findHandlerByHandlerKey(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }
}
