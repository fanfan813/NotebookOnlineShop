package com.amelin.shop.controller;

import com.amelin.shop.exception.ResourceNotFoundException;
import com.amelin.shop.model.Notebook;
import com.amelin.shop.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    NotebookService notebookService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public String mainPage(Model model) {
        return "layout_admin";
    }

    @RequestMapping(value = { "/notebooks", "/notebooks/" }, method = RequestMethod.GET)
    public String listAllNotebooks(Model model) {
        List<Notebook> notebooks = notebookService.findAll();
        model.addAttribute("notebooks", notebooks);
        return "admin/notebooks/list";
    }

    @RequestMapping(value = { "/notebooks/add", "/notebooks/add/" }, method = RequestMethod.GET)
    public String addNotebook(Model model) {
        Notebook notebook = new Notebook();
        model.addAttribute("notebook", notebook);
        model.addAttribute("actionUrl", "/admin/notebooks/add");
        return "admin/notebooks/add";
    }

    @RequestMapping(value = {"/notebooks/add", "/notebooks/add/"}, method = RequestMethod.POST)
    public String addNotebook(Notebook notebook, BindingResult result) {
        if (result.hasErrors()) {
            // обработка ошибки
        }
        notebookService.saveNotebook(notebook);
        return "redirect:/admin/notebooks/";
    }

    @RequestMapping(value = { "/notebooks/{id}", "/notebooks/{id}/"}, method = RequestMethod.GET)
    public String modifyNotebook(Model model, @PathVariable int id) {
        Notebook notebook = notebookService.findById(id);
        if (notebook == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("notebook", notebook);
        model.addAttribute("isEditNotebook", true);
        model.addAttribute("actionUrl", "/admin/notebooks/edit");
        return "admin/notebooks/add";
    }

    @RequestMapping(value = { "/notebooks/edit", "/notebooks/edit/"}, method = RequestMethod.POST)
    public ModelAndView updateNotebook(Model model, Notebook notebook, BindingResult result) {
        if (result.hasErrors()) {
            // обработка ошибок
        }
        notebookService.updateNotebook(notebook);

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/notebooks");
        modelAndView.addObject("isUpdated", true);
        return modelAndView;
    }

    @RequestMapping(value = { "/notebooks/delete/{id}", "/notebooks/delete/{id}/"}, method = RequestMethod.GET)
    public ModelAndView deleteNotebook(Model model, @PathVariable int id) {
        Notebook notebook = notebookService.findById(id);

        if (notebook != null) {
            notebookService.deleteNotebook(notebook);
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/notebooks");
        modelAndView.addObject("isDeleted", true);
        return modelAndView;
    }
}
