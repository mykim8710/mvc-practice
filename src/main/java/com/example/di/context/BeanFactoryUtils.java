package com.example.di.context;

import com.example.di.annotation.CustomInject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {

    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // 클래스타입 객체의 모든 생성자를 가지고 온다. 단 @Inject이 붙은 생성자
        Set<Constructor> injectedConstructor = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(CustomInject.class));
        if(injectedConstructor.isEmpty()) {
            return null;
        }

        return injectedConstructor.iterator().next();   // 있다면 첫번째 리턴(조건이 필요함)
    }
}
