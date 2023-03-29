package com.example.di.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;

        // 초기화 메서드 : beans를 초기화
        initBeans();
    }

    private void initBeans() {
        for (Class<?> clazz : preInstantiatedClazz) {
            // 클래스타입을 전달해서 해당 객체를 생성
            Object object = createInstance(clazz);
            beans.put(clazz, object);
        }
    }

    private Object createInstance(Class<?> clazz) {
        // 생성자 : 클래스 타입으로 생성자를 조회한다.
        Constructor<?> constructor = findConstructor(clazz);

        // 생성자의 파라미터 정보를 가지고 온다.
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getParameterByClass(typeClass));
        }

        // 인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        // @Inject가 붙은 생성자를 가지고 온다.
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if(Objects.nonNull(constructor)) {
            return constructor;
        }

        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);
        if(Objects.nonNull(instanceBean)) {
            return  instanceBean;
        }
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<T> requiredClassType) {
        return (T) beans.get(requiredClassType);
    }
}
