package com.example.practice.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)   // 타입에 적용 class, interface...
@Retention(RetentionPolicy.RUNTIME) // runtime 시점에 유지
public @interface ReflectController {
}
