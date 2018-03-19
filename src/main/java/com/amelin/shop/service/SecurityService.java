package com.amelin.shop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {
    String getUsername();
    void login(HttpServletRequest request, HttpServletResponse response, String login, String password);
    void logout(HttpServletRequest request, HttpServletResponse response);
    boolean isLoggedIn();
    boolean isAdmin();
    boolean isUser();
}
