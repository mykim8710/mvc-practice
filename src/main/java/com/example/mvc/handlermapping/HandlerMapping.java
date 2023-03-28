package com.example.mvc.handlermapping;

import com.example.mvc.HandlerKey;

public interface HandlerMapping {
    Object findHandlerByHandlerKey(HandlerKey handlerKey);
}
