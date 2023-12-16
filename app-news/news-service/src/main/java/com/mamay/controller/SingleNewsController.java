package com.mamay.controller;

import com.mamay.dao.CommentDao;
import com.mamay.dto.NewsDto;
import com.mamay.entity.CommentEntity;
import com.mamay.service.NewsManagementService;
import com.mamay.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = {"filteredItem"})
public class SingleNewsController {

    @Autowired
    private NewsManagementService newsManageService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentDao commentService;

    @GetMapping(value = "/news/{newsId}")
    public ModelAndView loadById(@PathVariable Long newsId,
                                 @ModelAttribute("filteredItem") String filteredItem) {
        ModelAndView model = new ModelAndView("news/item");
        NewsDto newsObject = newsManageService.loadById(newsId);
        model.addObject("newsObject", newsObject);
        model.addObject("comment", new CommentEntity());
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
