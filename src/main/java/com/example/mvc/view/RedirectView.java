package com.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectView implements View{

    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private final String viewPath;

    public RedirectView(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // "redirect:/user" -> /users
        response.sendRedirect(viewPath.substring(DEFAULT_REDIRECT_PREFIX.length()));
    }
}
