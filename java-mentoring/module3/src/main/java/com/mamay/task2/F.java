package com.mamay.task2;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Log4j2
public class F implements InitializingBean, DisposableBean {

    public void init() {
        log.info("Entering F.init method");
    }

    public void destroy() {
        log.info("Entering F.destroy method");
    }

    public void destroyMethod() {
        log.info("Entering custom F.destroy method");
    }

    public void afterPropertiesSet() throws Exception {
        log.info("Entering F.afterPropertiesSet method");
    }

}
