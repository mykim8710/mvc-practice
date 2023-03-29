package com.example.di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})   // 사용부분 : 생성자, 필드, 메서드
@Retention(RetentionPolicy.RUNTIME)// 유지기간 : 런타임
public @interface CustomInject {
}
