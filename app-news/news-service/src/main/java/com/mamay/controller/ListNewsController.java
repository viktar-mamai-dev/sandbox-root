package com.mamay.controller;

import com.mamay.dao.AuthorDao;
import com.mamay.dao.NewsDao;
import com.mamay.dao.TagDao;
import com.mamay.dto.NewsDto;
import com.mamay.dto.NewsPageItem;
import com.mamay.service.NewsManagementService;
import com.mamay.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@SessionAttributes(value = {"filteredItem", "sourcePage"})
@RequestMapping("news")
public class ListNewsController {

    private final int NEWS_PER_PAGE = 6;
    @Autowired
    private NewsManagementService newsManageService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private TagDao tagService;
    @Autowired
    private AuthorDao authorService;
    @Autowired
    private MessageSource messageSource;

    /*
    @GetMapping(value = {"/page/{page}"})
    public ModelAndView loadAll(@PathVariable(value = "page") Integer pageNumber,
                                ModelAndView model,
                                HttpServletRequest request) {
        NewsSearchCriteria filteredItem = new NewsSearchCriteria(null, null);
        model.addObject("filteredItem", filteredItem);
        model.setViewName("news/list");

        NewsPageItem<NewsDto> item = newsManageService.loadByFilter(filteredItem, pageNumber, NEWS_PER_PAGE);
        prepareModel(model, request, item);
        model.addObject("sourcePage", "/news/page/" + pageNumber);
        return model;
    }

    @PostMapping(value = "/filter")
    public String loadByFilter(Model model,
                               @RequestParam(value = "tagId", required = false) List<Long> tagIdList,
                               @RequestParam(value = "authorId", required = false) Long authorId) {
        model.addAttribute("filteredItem", new NewsSearchCriteria(tagIdList, authorId));
        return "redirect:/news/filter/1";
    }

    @GetMapping(value = "/filter/{page}")
    public ModelAndView loadByFilterPageable(@ModelAttribute("filteredItem") NewsSearchCriteria filteredItem,
                                             @PathVariable(value = "page") Integer pageNumber,
                                             HttpServletRequest request) {
        ModelAndView model = new ModelAndView("news/list");
        NewsPageItem<NewsDto> item = newsManageService.loadByFilter(filteredItem, pageNumber, NEWS_PER_PAGE);
        prepareModel(model, request, item);
        model.addObject("isFilter", true);
        model.addObject("sourcePage", "/news/filter/" + pageNumber);
        return model;
    }
    */

    @PostMapping(value = "/delete")
    public String delete(@RequestParam(value = "newsId", required = false) List<Long> newsIdList,
                         @RequestParam(value = "pageNumber") Integer pageNumber,
                         RedirectAttributes ra,
                         HttpServletRequest request) {
        newsDao.deleteList(newsIdList);
        Locale locale = RequestContextUtils.getLocale(request);
        ra.addFlashAttribute("successMessage",
                messageSource.getMessage("message.news.delete", new Object[]{newsIdList.size()}, locale));
        return "redirect:/news/page/" + pageNumber;
    }

    private void prepareModel(ModelAndView model, HttpServletRequest request, NewsPageItem<NewsDto> item) {
        Locale locale = RequestContextUtils.getLocale(request);
        if (item.getNewsList().isEmpty()) {
            model.addObject("errorEmptyMessage",
                    messageSource.getMessage("message.news.empty", null, locale));
        }
        model.addObject("newsItem", item);
        model.addObject("tagList", tagService.loadAll());
        model.addObject("authorList", authorService.loadAll());
    }
}
