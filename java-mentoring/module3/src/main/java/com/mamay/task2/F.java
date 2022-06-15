package com.mamay.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class F implements InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(F.class);

    public void init() {
        LOGGER.info("Entering F.init method");
    }

    public void destroy() {
        LOGGER.info("Entering F.destroy method");
    }
    
    public void destroyMethod() {
        LOGGER.info("Entering custom F.destroy method");
    }

    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Entering F.afterPropertiesSet method");
    }

}
