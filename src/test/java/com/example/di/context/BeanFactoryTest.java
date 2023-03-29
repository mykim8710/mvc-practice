package com.example.di.context;

import com.example.di.annotation.CustomController;
import com.example.di.annotation.CustomService;
import com.example.di.controller.FooController;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        // 초기화
        reflections = new Reflections("com.example.di"); // 입력한 package하부의 클래스들에 리플렉션 기술을 사용

        // getTypesAnnotatedWith : 해당 에노테이션(@CustomController, @CustomService)을 가지는 클래스들을 가지고옴
        // FooController, FooService
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(CustomController.class, CustomService.class);

        // 초기화
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    // Class<? extends Annotation>... annotations : annotation타입의 객체가 여러게 올수 있다.
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();

        // @Controller이 붙은 클래스타입 객체를 조회해서 beans add
        // @Service이 붙은 클래스타입 객체를 조회해서 beans add
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

    @Test
    @DisplayName("초기화 된 BeanFactory에서 FooController와 FooController에 주입된 UserService를 가지고온다.")
    void getFooControllerFromBeanFactoryTest() throws Exception {
        FooController customController = beanFactory.getBean(FooController.class);

        //FooController는 null이 아니다.
        Assertions.assertThat(customController).isNotNull();

        // @CustomInject가 되었다면 (di) FooService는 null이 아니다.
        Assertions.assertThat(customController.getFooService()).isNotNull();
    }

}