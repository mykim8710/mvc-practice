package com.example.mvc.handlermapping;

import com.example.mvc.AnnotationHandler;
import com.example.mvc.HandlerKey;
import com.example.mvc.annotation.RequestMapping;
import com.example.mvc.enums.RequestHttpMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackages;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>(); // key : urlPath, value : AnnotationHandler

    public AnnotationHandlerMapping(Object... basePackages) {
        this.basePackages = basePackages;
    }

    public void init() {
        Reflections reflections = new Reflections(basePackages);

        // com.example.mvc 하부 @Controller 어노테이션이있는 클래스를 모두 가지고 온다.
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(com.example.mvc.annotation.Controller.class);

        clazzWithControllerAnnotation.forEach(clazz -> {

            Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
                // 메서드를 돌면서 @RequestMapping 어노테이션을 찾아서 uriPath, HttpRequestMethod 값을 찾는다.
                // @RequestMapping(value = "/v2/users", method = RequestHttpMethod.GET)
                RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);

                Arrays.stream(getRequestHttpMethods(requestMapping))
                        .forEach(requestHttpMethod -> {
                            HandlerKey handlerKey = new HandlerKey(requestMapping.value(), requestHttpMethod);
                            handlers.put(handlerKey, new AnnotationHandler(clazz, method));
                        });
            });
        });
    }

    private RequestHttpMethod[] getRequestHttpMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandlerByHandlerKey(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
