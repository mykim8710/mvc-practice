package com.example.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private String viewPath;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getViewPath() {
        return this.viewPath;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);  // 불변객체로 리턴
    }
}
