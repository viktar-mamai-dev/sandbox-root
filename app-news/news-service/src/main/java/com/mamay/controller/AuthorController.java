package com.mamay.controller;


import com.mamay.entity.AuthorEntity;
import com.mamay.exception.ControllerException;
import com.mamay.exception.ServiceException;
import com.mamay.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/author/list", method = RequestMethod.GET)
    public ModelAndView loadAll() throws ControllerException {
        try {
            ModelAndView view = new ModelAndView("author");
            List<AuthorEntity> authorList = authorService.loadAll();
            view.addObject("authorList", authorList);
            view.addObject("authorEntity", new AuthorEntity());
            return view;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMapping(value = "/author/create", method = RequestMethod.POST)
    public String create(@ModelAttribute AuthorEntity author,
                         RedirectAttributes ra, HttpServletRequest request)
            throws ControllerException {
        try {
            authorService.create(author);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.author.add", new Object[]{author.getName()},
                    locale));
            return "redirect:/author/list";
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMapping(value = "/author/update", method = RequestMethod.POST)
    public String update(@ModelAttribute AuthorEntity author,
                         RedirectAttributes ra, HttpServletRequest request)
            throws ControllerException {
        try {
            authorService.update(author);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.author.update", new Object[]{author.getName()},
                    locale));
            return "redirect:/author/list";
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @RequestMapping(value = "/author/expired", method = RequestMethod.POST)
    public String expired(@RequestParam("id") Long authorId,
                          RedirectAttributes ra, HttpServletRequest request)
            throws ControllerException {
        try {
            authorService.makeExpired(authorId);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.author.expired", null, locale));
            return "redirect:/author/list";
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
