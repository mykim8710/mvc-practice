package com.example.mvc.annotation;

import com.example.mvc.enums.RequestHttpMethod;
import com.example.practice.reflection.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
    RequestHttpMethod[] method() default {};
}
