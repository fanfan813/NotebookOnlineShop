package com.amelin.shop.controller;

import com.amelin.shop.exception.ResourceNotFoundException;
import com.amelin.shop.model.Account;
import com.amelin.shop.model.Comment;
import com.amelin.shop.model.Notebook;
import com.amelin.shop.service.AccountService;
import com.amelin.shop.service.CommentService;
import com.amelin.shop.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    AccountService accountService;

    @Autowired
    NotebookService notebookService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String mainPage(Model model) {
        String login = getPrincipal();
        if (login != null) {
            Account account = accountService.findByLogin(login);
            model.addAttribute("username", account.getFirstName());
        }
        return "main";
    }

    @RequestMapping(value = { "/catalog", "/catalog/" }, method = RequestMethod.GET)
    public String calatogPage(Model model) {
        return "";
    }

    @RequestMapping(value = { "/payment", "/payment/" }, method = RequestMethod.GET)
    public String paymentPage(Model model) {
        return "payment";
    }

    @RequestMapping(value = { "/delivery", "/delivery/" }, method = RequestMethod.GET)
    public String deliveryPage(Model model) {
        return "delivery";
    }

    @RequestMapping(value = { "/contacts", "/contacts/" }, method = RequestMethod.GET)
    public String contactsPage(Model model) {
        return "contacts";
    }

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET)
    public String listUsersPage(Model model) {
        List<Account> userList = accountService.getAllUsers();
        model.addAttribute("users", userList);
        return "users";
    }

    @RequestMapping(value = { "/notebooks/{id}", "/notebooks/{id}/"}, method = RequestMethod.GET)
    public String showNotebook(Model model, @PathVariable int id) {
        Notebook notebook = notebookService.findById(id);
        if (notebook == null) {
            throw new ResourceNotFoundException();
        }

        List<Comment> comments = commentService.findCommentsByNotebook(notebook);
        model.addAttribute("notebook", notebook);
        model.addAttribute("comments", comments);
        return "notebook";
    }

    @RequestMapping(value = { "/comment/{id}", "/comments/{id}/" }, method = RequestMethod.GET)
    public String showComment(Model model, @PathVariable int id) {
        Comment comment = commentService.findCommentById(id);
        if (comment == null) {
            throw new ResourceNotFoundException();
        }
        return "main";
    }

    private String getPrincipal() {
        String login = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
        }
        return login;
    }
}
