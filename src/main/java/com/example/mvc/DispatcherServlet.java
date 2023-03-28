package com.example.mvc;

import com.example.mvc.enums.RequestHttpMethod;
import com.example.mvc.handleradapter.AnnotationHandlerAdapter;
import com.example.mvc.handleradapter.HandlerAdapter;
import com.example.mvc.handleradapter.SimpleControllerHandlerAdapter;
import com.example.mvc.handlermapping.AnnotationHandlerMapping;
import com.example.mvc.handlermapping.HandlerMapping;
import com.example.mvc.handlermapping.RequestHandlerMapping;
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
import java.util.Optional;

/**
 * 클라이언트로부터 요청이 오면 가장먼저 DispatcherServlet이 작동
 * "/"
 */
@WebServlet("/") // 어떤 경로로 요청이 들어와 이 서블릿이 실행
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private Map<String, ViewResolver> viewResolvers = new HashMap<>();

    // DispatcherServlet의 초기화 메서드(최초 한번 실행)
    @Override
    public void init() throws ServletException {
        // handlerMapping 초기화
        // [in memory(static, hard coding) HandlerMapping]
        RequestHandlerMapping requestHandlerMapping = new RequestHandlerMapping();
        requestHandlerMapping.init();

        // annotation 방식 HandlerMapping
        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("com.example.mvc");
        annotationHandlerMapping.init();

        handlerMappings = List.of(requestHandlerMapping, annotationHandlerMapping);


        // HandlerAdapter 등록(초기화)
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());

        // ViewResolver 등록(초기화)
        // ViewResolver가 여러개임을 가정, 예제에서는 JSP만 사용할 예정
        viewResolvers.put("JSP", new JspViewResolver());
        viewResolvers.put("HTML", new HtmlResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String requestURI = request.getRequestURI();
        final RequestHttpMethod requestHttpMethod = RequestHttpMethod.valueOf(request.getMethod());

        logger.info("[DispatcherServlet] service started. urlPath : {}, httpMethod : {}", requestURI, requestHttpMethod);    // DispatcherServlet이 모든 요청을 받음

        try {
            // 1. handlerMapping에서 handler룰 찾는다.(handlerKey 맞는 handler(Controller) 반환 + 비지니스 로직)
            HandlerKey handlerKey = new HandlerKey(requestURI, requestHttpMethod);
            Object handler = handlerMappings.stream()
                                            .filter(hm -> hm.findHandlerByHandlerKey(handlerKey) != null)
                                            .map(hm -> hm.findHandlerByHandlerKey(handlerKey))
                                            .findFirst()
                                            .orElseThrow(() -> new ServletException("No handler for [" + requestURI + ", " + requestHttpMethod + "]"));



            // 2. HandlerAdapter에서 해당 handler를 지원하는지 확인한 뒤
            HandlerAdapter adapter = handlerAdapters.stream()
                                                    .filter(handlerAdapter -> handlerAdapter.support(handler))
                                                    .findFirst()
                                                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            // 지원한다면 HandlerAdapter가 handler를 실행하고 ModelAndView 리턴받는다.
            ModelAndView modelAndView = adapter.handler(request, response, handler);
            /**
             * ModelAndView
             *  {
             *      "viewPath": viewPath,
             *      "model" : {   }
             *  }
             */


            // 3. 해당하는 ViewResolver를 가져온다.
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
