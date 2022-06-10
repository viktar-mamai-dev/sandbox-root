package com.mamay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
							  HttpServletRequest request) {

        Locale locale = RequestContextUtils.getLocale(request);
        ModelAndView model = new ModelAndView("login");
        if (error != null) {
            model.addObject("badCredentials",
                    messageSource.getMessage("message.error.badCredentials", null, locale));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (logout != null && auth != null && auth.getName() != null) {
            model.addObject("logoutMessage",
                    messageSource.getMessage("message.logout", null, locale));
        }

        return model;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView forbidden() {

        ModelAndView model = new ModelAndView("error/403");

        // check if user is logged in
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
            model.addObject("role", userDetail.getAuthorities());
        }
        return model;
    }
}
