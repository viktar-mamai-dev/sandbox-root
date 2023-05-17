package com.mamay.controller;

import com.mamay.dto.NewsDto;
import com.mamay.dto.NewsSearchCriteria;
import com.mamay.entity.CommentEntity;
import com.mamay.service.CommentService;
import com.mamay.service.NewsManagementService;
import com.mamay.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = {"filteredItem"})
public class SingleNewsController {

    @Autowired
    private NewsManagementService newsManageService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/news/next/{newsId}")
    public String loadNextNews(@ModelAttribute("filteredItem") NewsSearchCriteria filteredItem,
                               @PathVariable Long newsId) {
            Long nextId = newsService.loadNextId(filteredItem, newsId);
            if (nextId == null) {
                nextId = newsId;
            }
            return "redirect:/news/" + nextId;
    }

    @GetMapping(value = "/news/previous/{newsId}")
    public String loadPreviousNews(@ModelAttribute("filteredItem") NewsSearchCriteria filteredItem,
                                   @PathVariable Long newsId) {
            Long previousId = newsService.loadPreviousId(filteredItem, newsId);
            if (previousId == null) {
                previousId = newsId;
            }
            return "redirect:/news/" + previousId;
    }

    @GetMapping(value = "/news/{newsId}")
    public ModelAndView loadById(@PathVariable Long newsId,
                                 @ModelAttribute("filteredItem") NewsSearchCriteria filteredItem) {
            ModelAndView model = new ModelAndView("news/item");
            NewsDto newsObject = newsManageService.loadById(newsId);
            model.addObject("newsObject", newsObject);
            model.addObject("comment", new CommentEntity());
            Long nextId = newsService.loadNextId(filteredItem, newsId);
            Long previousId = newsService.loadPreviousId(filteredItem, newsId);
            model.addObject("nextId", nextId);
            model.addObject("previousId", previousId);
            return model;
    }

    @PostMapping(value = "/comment/create")
    public String createComment(@ModelAttribute("comment") CommentEntity entity) {
            commentService.create(entity);
            return "redirect:/news/" + entity.getNews().getId();
    }

    @DeleteMapping(value = "/comment/delete")
    public String delete(@RequestParam("commentId") Long commentId, @RequestParam("newsId") Long newsId) {
            commentService.delete(commentId);
            return "redirect:/news/" + newsId;
    }
}
