package com.amelin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("SecurityService")
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenBasedRememberMeServices rememberMeServices;

    public String getUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        } else {
            return null;
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response, String login, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                password, userDetails.getAuthorities());

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        rememberMeServices.loginSuccess(request, response, token);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            rememberMeServices.logout(request, response, authentication);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication instanceof UsernamePasswordAuthenticationToken)
                || (authentication instanceof RememberMeAuthenticationToken);
    }

    public boolean isAdmin() {
        return getRole("ROLE_ADMIN");
    }

    public boolean isUser() {
        return getRole("ROLE_USER");
    }

    private boolean getRole(String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}
