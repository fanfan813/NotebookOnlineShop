package com.amelin.shop.controller;

import com.amelin.shop.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ErrorController {
    @RequestMapping(value = { "/403", "/403/"}, method = RequestMethod.GET)
    public String show403() {
        return "403";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAccessDeniedException() {
        return "403";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "404";
    }
}
