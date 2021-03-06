package com.mamay.controller;

import com.mamay.entity.TagEntity;
import com.mamay.exception.ControllerException;
import com.mamay.exception.ServiceException;
import com.mamay.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("tag")
public class TagController {
    private static final String REDIRECT_URL = "redirect:/tag/list";
    @Autowired
    private TagService tagService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/list")
    public ModelAndView loadAll() throws ControllerException {
        try {
            ModelAndView view = new ModelAndView("tag");
            List<TagEntity> tagList = tagService.loadAll();
            view.addObject("tagList", tagList);
            view.addObject("tagEntity", new TagEntity());
            return view;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute TagEntity tag, RedirectAttributes ra,
                         HttpServletRequest request) throws ControllerException {
        try {
            tagService.create(tag);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.tag.add", new Object[]{tag.getName()}, locale));
            return REDIRECT_URL;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PutMapping(value = "/update")
    public String update(@ModelAttribute TagEntity tag, RedirectAttributes ra,
                         HttpServletRequest request) throws ControllerException {
        try {
            tagService.update(tag);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.tag.update", new Object[]{tag.getName()},
                    locale));
            return REDIRECT_URL;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestParam("id") Long tagId, RedirectAttributes ra,
                         HttpServletRequest request) throws ControllerException {
        try {
            tagService.delete(tagId);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage", messageSource.getMessage(
                    "message.tag.delete", null, locale));
            return REDIRECT_URL;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
