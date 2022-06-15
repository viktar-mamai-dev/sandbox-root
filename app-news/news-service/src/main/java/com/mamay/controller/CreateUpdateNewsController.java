package com.mamay.controller;

import com.mamay.dto.NewsDto;
import com.mamay.entity.AuthorEntity;
import com.mamay.entity.NewsEntity;
import com.mamay.exception.ControllerException;
import com.mamay.exception.ServiceException;
import com.mamay.service.AuthorService;
import com.mamay.service.NewsManagementService;
import com.mamay.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("news")
public class CreateUpdateNewsController {
    @Autowired
    private NewsManagementService newsManageService;
    @Autowired
    private TagService tagService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        Locale locale = RequestContextUtils.getLocale(request);
        SimpleDateFormat dateFormat = null;
        if (locale.getLanguage().equals("ru")) {
            dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        } else {
            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        }
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/create")
    public ModelAndView showFormToCreate() throws ControllerException {
        try {
            ModelAndView model = new ModelAndView("news/create");
            model.addObject("newsEntity", new NewsEntity());
            model.addObject("tagList", tagService.loadAll());
            model.addObject("authorList", authorService.loadActiveAuthors());
            return model;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PostMapping(value = "/create")
    public String create(
            @ModelAttribute("newsEntity") NewsEntity newsEntity,
            @RequestParam(value = "tagId", required = false) List<Long> tagIdList,
            @RequestParam("authorId") Long authorId, RedirectAttributes ra,
            HttpServletRequest request) throws ControllerException {
        try {
            Long newsId = newsManageService.create(newsEntity, tagIdList, authorId);
            Locale locale = RequestContextUtils.getLocale(request);
            ra.addFlashAttribute("successMessage",
                    messageSource.getMessage("message.news.add", null, locale));
            return "redirect:/news/" + newsId;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(value = "/update/{newsId}")
    public ModelAndView showFormToUpdate(@PathVariable("newsId") Long id)
            throws ControllerException {
        try {
            ModelAndView model = new ModelAndView("news/create");
            NewsDto newsObject = newsManageService.loadById(id);
            model.addObject("newsObject", newsObject);
            model.addObject("tagList", tagService.loadAll());
            List<AuthorEntity> authorList = authorService.loadActiveAuthors();
            AuthorEntity authorEntity = newsObject.getAuthorEntity();
            if (authorEntity.getExpiredDate() != null) {
                authorList.add(authorEntity);
            }
            model.addObject("authorList", authorList);
            return model;
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @PostMapping(value = "/update")
    public String update(
            @ModelAttribute("newsEntity") NewsEntity newsEntity,
            @RequestParam(value = "tagId", required = false) List<Long> tagIdList,
            @RequestParam("authorId") Long authorId, RedirectAttributes ra,
            HttpServletRequest request) throws ServiceException {
        newsManageService.update(newsEntity, tagIdList, authorId);

        Locale locale = RequestContextUtils.getLocale(request);
        ra.addFlashAttribute("successMessage",
                messageSource.getMessage("message.news.update", null, locale));
        return "redirect:/news/page/1";
    }
}
