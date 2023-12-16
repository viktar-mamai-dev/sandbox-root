package com.mamay.controller;

import com.mamay.exception.NewsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NewsException.class})
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        log.error(ex);
        ModelAndView modelAndView = new ModelAndView("error/controller");
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }
}
