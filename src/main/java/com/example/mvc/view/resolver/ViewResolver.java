package com.example.mvc.view.resolver;

import com.example.mvc.view.View;

/**
 * viewName을 받아서 view를 결정
 */
public interface ViewResolver {
    View resolver(String viewName);
}
