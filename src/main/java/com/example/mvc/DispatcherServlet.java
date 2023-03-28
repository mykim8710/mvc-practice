package com.example.mvc;

import com.example.mvc.controller.Controller;
import com.example.mvc.enums.RequestHttpMethod;
import com.example.mvc.view.ModelAndView;
import com.example.mvc.view.View;
import com.example.mvc.view.resolver.HtmlResolver;
import com.example.mvc.view.resolver.JspViewResolver;
import com.example.mvc.view.resolver.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 클라이언트로부터 요청이 오면 가장먼저 DispatcherServlet이 작동
 * "/"
 */
@WebServlet("/") // 어떤 경로로 요청이 들어와 이 서블릿이 실행
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestHandlerMapping handlerMapping;

    private List<HandlerAdapter> handlerAdapters;
    private Map<String, ViewResolver> viewResolvers = new HashMap<>();

    //List<ViewResolver> resolvers;


    @Override
    public void init() throws ServletException {
        handlerMapping = new RequestHandlerMapping();
        handlerMapping.init();   // 초기화


        // HandlerAdapter 등록(초기화)
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter());


        // ViewResolver 등록(초기화)
        //resolvers = Collections.singletonList(new JspViewResolver()); // 인자가 하나일 경우
        //resolvers = List.of(new JspViewResolver(), new HtmlResolver());// 인자가 여러개일 경우

        // ViewResolver가 여러개임을 가정, 예제에서는 JSP만 사용할 예정
        viewResolvers.put("JSP", new JspViewResolver());
        viewResolvers.put("HTML", new HtmlResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("[DispatcherServlet] service started. urlPath : {}, httpMethod : {}", request.getRequestURI(), request.getMethod());    // DispatcherServlet이 모든 요청을 받음

        try {
            // 1. handlerMapping에서 handler룰 찾는다.(handlerKey 맞는 handler(Controller) 반환 + 비지니스 로직)
            Controller handler = handlerMapping.findHandlerByHandlerKey(new HandlerKey(request.getRequestURI(), RequestHttpMethod.valueOf(request.getMethod())));

            // 2. HandlerAdapter에서 이 handler를 지원하는지 확인한 뒤
            HandlerAdapter adapter = handlerAdapters.stream()
                                                    .filter(handlerAdapter -> handlerAdapter.support(handler))
                                                    .findFirst()
                                                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            // 지원한다면 handler를 실행하고 ModelAndView 리턴받는다.
            ModelAndView modelAndView = adapter.handler(request, response, handler);
            /**
             * ModelAndView
             *  {
             *      "viewPath": viewPath,
             *      "model" : {   }
             *  }
             */


            // ViewResolver가 여러개라면 당연히 Map에서 맞는 ViewResolver를 가져오는 로직이 필요하지만 지금은 JSP만사용하므로 아래처럼 진행
            /*for (ViewResolver resolver: resolvers) {
                View view = resolver.resolver(viewPath);
                view.render(new HashMap<>(), request, response);
            }*/

            // 3. 맞는 ViewResolver를 가져온다.
            JspViewResolver jspViewResolver = (JspViewResolver)viewResolvers.get("JSP");

            // 4. ViewResolver에서 viewPath에 맞는 View를 리턴받는다.
            View view = jspViewResolver.resolver(modelAndView.getViewPath());

            // 5. View를 랜더링 한다.
            view.render(modelAndView.getModel(), request, response);
        } catch (Exception e) {
            logger.error("exception : {}", e.getMessage(), e);
        }
    }
}
