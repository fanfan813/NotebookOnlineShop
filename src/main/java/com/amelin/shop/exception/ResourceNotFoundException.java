package com.amelin.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
public class ResourceNotFoundException extends RuntimeException {
    private String msg;

    public ResourceNotFoundException() {
        super();
    }

    public String getMsg() {
        return msg;
    }
}
