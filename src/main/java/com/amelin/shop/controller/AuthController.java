package com.amelin.shop.controller;

import com.amelin.shop.model.Account;
import com.amelin.shop.model.AccountTypeEnum;
import com.amelin.shop.service.AccountService;
import com.amelin.shop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/auth")
@SessionAttributes("roles")
public class AuthController {
    @Autowired
    AccountService accountService;

    @Autowired
    SecurityService securityService;

    @Autowired
    PersistentTokenBasedRememberMeServices rememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout) {
        if (!isCurrentAuthenticationAnonymous()) {
            return "redirect:/";
        }

        Account account = new Account();
        model.addAttribute("account", account);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "auth/login";
    }

    @RequestMapping(value = { "/logout", "/logout/" }, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        securityService.logout(request, response);
        ModelAndView modelAndView = new ModelAndView("redirect:/auth/login?logout");
        return modelAndView;
    }

    @RequestMapping(value = { "/reg", "/reg/"}, method = RequestMethod.GET)
    public String showRegForm(ModelMap model) {
        if (!isCurrentAuthenticationAnonymous()) {
            return "redirect:/";
        }

        Account account = new Account();
        model.addAttribute("account", account);
        return "auth/reg";
    }

    @RequestMapping(value = { "/reg", "/reg/" }, method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, HttpServletResponse response,
                               ModelMap model, Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/reg";
        }

        account.setAccountType(AccountTypeEnum.USER);
        accountService.saveUser(account);
        securityService.login(request, response, account.getLogin(), account.getPassword());
        return "redirect:/";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
