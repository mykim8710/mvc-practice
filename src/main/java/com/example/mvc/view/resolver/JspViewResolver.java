package com.example.mvc.view.resolver;

import com.example.mvc.view.JspView;
import com.example.mvc.view.RedirectView;
import com.example.mvc.view.View;

import static com.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver{

    public static final String JSP_EXTENSION = ".jsp";

    /**
     * viewPath ex)
     *  - /user/list
     *  - /user/form
     *  .......
     *
     * -  redirect:/users
     */

    @Override
    public View resolver(String viewPath) {
        if(viewPath.startsWith(DEFAULT_REDIRECT_PREFIX)) { // DEFAULT_REDIRECT_PREFIX : "redirect:"
            return new RedirectView(viewPath);
        }

        return new JspView(viewPath + JSP_EXTENSION); //viewPath + JSP_EXTENSION : /user/list.jsp......
    }
}
