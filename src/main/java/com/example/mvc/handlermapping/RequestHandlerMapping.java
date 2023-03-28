package com.example.mvc.handlermapping;

import com.example.mvc.HandlerKey;
import com.example.mvc.controller.v1.*;
import com.example.mvc.enums.RequestHttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 경로(uriPath)에 따른 핸들러(controller)를 가지고 있음
 * DispatcherServlet으로 부터 경로(uriPath)에 따른 핸들러(controller)를 리턴해줌
 */

public class RequestHandlerMapping implements HandlerMapping {
    private Map<HandlerKey, Controller> mappings = new HashMap<>(); // key : urlPath, value : Handler(controller)

    public void init() {
        mappings.put(new HandlerKey("/v1", RequestHttpMethod.GET), new MainController());
        mappings.put(new HandlerKey("/v1/users", RequestHttpMethod.GET), new UserListViewController());
        mappings.put(new HandlerKey("/v1/users/form", RequestHttpMethod.GET), new ForwardController("/userV1/form"));
        mappings.put(new HandlerKey("/v1/users", RequestHttpMethod.POST), new UserFormController());
    }

    // handlerKey에 맞는 Controller를 반환하는 메서드
    /**
     * handlerKey = {
     *                  "uriPath" : "/users",
     *                  "requestMethod" : "GET"
     *              }
     */

    @Override
    public Controller findHandlerByHandlerKey(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }
}
