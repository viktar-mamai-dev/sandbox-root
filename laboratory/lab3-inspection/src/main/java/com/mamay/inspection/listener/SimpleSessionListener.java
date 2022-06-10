package com.mamay.inspection.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Log4j2
public class SimpleSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        log.debug("session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        log.debug("session destroyed");
    }

}
