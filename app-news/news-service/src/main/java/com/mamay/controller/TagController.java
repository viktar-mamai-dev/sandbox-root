package com.mamay.controller;

import com.mamay.dao.TagDao;
import com.mamay.entity.TagEntity;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("tag")
public class TagController {
  private static final String REDIRECT_URL = "redirect:/tag/list";
  @Autowired private TagDao tagDao;
  @Autowired private MessageSource messageSource;

  @GetMapping(value = "/list")
  public ModelAndView loadAll() {
    ModelAndView view = new ModelAndView("tag");
    List<TagEntity> tagList = tagDao.loadAll();
    view.addObject("tagList", tagList);
    view.addObject("tagEntity", new TagEntity());
    return view;
  }

  @PostMapping(value = "/create")
  public String create(
      @ModelAttribute TagEntity tag, RedirectAttributes ra, HttpServletRequest request) {
    tagDao.create(tag);
    Locale locale = RequestContextUtils.getLocale(request);
    ra.addFlashAttribute(
        "successMessage",
        messageSource.getMessage("message.tag.add", new Object[] {tag.getName()}, locale));
    return REDIRECT_URL;
  }

  @PutMapping(value = "/update")
  public String update(
      @ModelAttribute TagEntity tag, RedirectAttributes ra, HttpServletRequest request) {
    tagDao.update(tag);
    Locale locale = RequestContextUtils.getLocale(request);
    ra.addFlashAttribute(
        "successMessage",
        messageSource.getMessage("message.tag.update", new Object[] {tag.getName()}, locale));
    return REDIRECT_URL;
  }

  @DeleteMapping(value = "/delete")
  public String delete(
      @RequestParam("id") Long tagId, RedirectAttributes ra, HttpServletRequest request) {
    tagDao.delete(tagId);
    Locale locale = RequestContextUtils.getLocale(request);
    ra.addFlashAttribute(
        "successMessage", messageSource.getMessage("message.tag.delete", null, locale));
    return REDIRECT_URL;
  }
}
