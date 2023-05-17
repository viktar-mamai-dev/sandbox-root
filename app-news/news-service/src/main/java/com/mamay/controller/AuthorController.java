package com.mamay.controller;


import com.mamay.entity.AuthorEntity;
import com.mamay.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("author")
public class AuthorController {

    private static final String REDIRECT_URL = "redirect:/author/list";
    @Autowired
    private AuthorService authorService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/list")
    public ModelAndView loadAll() {
        ModelAndView view = new ModelAndView("author");
        List<AuthorEntity> authorList = authorService.loadAll();
        view.addObject("authorList", authorList);
        view.addObject("authorEntity", new AuthorEntity());
        return view;
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute AuthorEntity author, RedirectAttributes ra, HttpServletRequest request) {
        authorService.create(author);
        Locale locale = RequestContextUtils.getLocale(request);
        ra.addFlashAttribute("successMessage", messageSource.getMessage(
                "message.author.add", new Object[]{author.getName()},
                locale));
        return REDIRECT_URL;
    }

    @PutMapping(value = "/update")
    public String update(@ModelAttribute AuthorEntity author,
                         RedirectAttributes ra, HttpServletRequest request) {
        authorService.update(author);
        Locale locale = RequestContextUtils.getLocale(request);
        ra.addFlashAttribute("successMessage", messageSource.getMessage(
                "message.author.update", new Object[]{author.getName()},
                locale));
        return REDIRECT_URL;
    }

    @PutMapping(value = "/expired")
    public String expired(@RequestParam("id") Long authorId, RedirectAttributes ra, HttpServletRequest request) {
        authorService.makeExpired(authorId);
        Locale locale = RequestContextUtils.getLocale(request);
        ra.addFlashAttribute("successMessage",
                messageSource.getMessage("message.author.expired", null, locale));
        return REDIRECT_URL;
    }
}
