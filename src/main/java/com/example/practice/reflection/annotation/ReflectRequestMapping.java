package com.example.practice.reflection.annotation;

import com.example.practice.reflection.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReflectRequestMapping {
    String value() default "";
    RequestMethod[] method() default {};
}
