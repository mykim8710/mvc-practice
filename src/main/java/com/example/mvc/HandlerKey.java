package com.example.mvc;

import com.example.mvc.enums.RequestHttpMethod;

import java.util.Objects;

/**
 * DispatcherServlet -> HandlerMappring, uriPath에 맞는 핸들러를 리턴
 *
 * uriPath에 맞는 핸들러를 리턴하기 위해 존재
 * uriPath, RequestHttpMethod(GET, POST....)
 *
 */
public class HandlerKey {
    private String uriPath;
    private RequestHttpMethod requestMethod;

    public HandlerKey(String uriPath, RequestHttpMethod requestMethod) {
        this.uriPath = uriPath;
        this.requestMethod = requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return Objects.equals(uriPath, that.uriPath) && requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uriPath, requestMethod);
    }

    @Override
    public String toString() {
        return "HandlerKey{" +
                "uriPath='" + uriPath + '\'' +
                ", requestMethod=" + requestMethod +
                '}';
    }
}
