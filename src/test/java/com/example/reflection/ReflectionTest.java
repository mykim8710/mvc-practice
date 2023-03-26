package com.example.reflection;

import com.example.WebApplicationServer;
import com.example.reflection.annotation.Controller;
import com.example.reflection.annotation.Service;
import com.example.reflection.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    @DisplayName("@Controller 어노테이션이 있는 클래스를 찾아서 출력한다.")
    void controllerScanTest() throws Exception{
        Reflections reflections = new Reflections("com.example");   // com.example 패키지 하부의 클래스에 대해 리플렉션을 사용할 것이다.

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));// @Controller 어노테이션이 포함된 클래스를 Set에 담는다.(중복 X, Set이기 때문)

        logger.debug("beans = {}", beans);
    }
    @Test
    @DisplayName("@Controller, @Service 어노테이션이 있는 클래스를 찾아서 출력한다.")
    void controllerAndServiceScanTest() throws Exception{
        Set<Class<?>> beans = getTypesWithAnnotation(List.of(Controller.class, Service.class));
        logger.debug("beans = {}", beans);
    }

    private static Set<Class<?>> getTypesWithAnnotation(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("com.example");   // com.example 패키지 하부의 클래스에 대해 리플렉션을 사용할 것이다.
        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
        return beans;
    }

    @Test
    @DisplayName("User Class의 정보를 가져온다.")
    void showClassInfoTest() throws Exception{
        Class<User> userClass = User.class;
        logger.debug("userClass.getName() = {}", userClass.getName());
        logger.debug("userClass all field = [{}]", Arrays.stream(userClass.getDeclaredFields()).collect(Collectors.toList()));  // 클래스 필드
        logger.debug("userClass all constructors = [{}]", Arrays.stream(userClass.getDeclaredConstructors()).collect(Collectors.toList())); // 생성자
        logger.debug("userClass all methods = [{}]", Arrays.stream(userClass.getDeclaredMethods()).collect(Collectors.toList()));   // 메서드
    }

    /**
     * Heap 영역에 로드돼 있는 클래스 타입의 객체를 가져오는 방법
     */

    @Test
    @DisplayName("Heap 영역에 로드돼 있는 User Class의 정보를 가져온다.")
    void getUserClassInfoOnHeapTest() throws Exception{
        // 1
        Class<User> userClazz = User.class;

        // 2
        User user = new User("id", "name");
        Class<? extends User> userClazz2 = user.getClass();

        // 3
        Class<?> userClazz3 = Class.forName("com.example.reflection.model.User");

        logger.debug("userClazz = [{}]", userClazz);
        logger.debug("userClazz2 = [{}]", userClazz2);
        logger.debug("userClazz3 = [{}]", userClazz3);

        // 세 객체 모두 같은 객체
        Assertions.assertThat(userClazz).isEqualTo(userClazz2);
        Assertions.assertThat(userClazz).isEqualTo(userClazz3);
        Assertions.assertThat(userClazz3).isEqualTo(userClazz3);
    }

}
