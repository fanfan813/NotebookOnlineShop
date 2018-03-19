package com.amelin.shop.controller;

import com.amelin.shop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthControllerAdvice {
    @Autowired
    SecurityService securityService;

    @ModelAttribute("isAuth")
    public boolean addAuthInformation() {
        return securityService.isLoggedIn();
    }

    @ModelAttribute("isAdmin")
    /*public boolean addAdminInformation(HttpServletRequest request) {
        return request.isUserInRole("ROLE_ADMIN");
    }*/
    public boolean addAdminInformation() {
        return securityService.isAdmin();
    }
}
