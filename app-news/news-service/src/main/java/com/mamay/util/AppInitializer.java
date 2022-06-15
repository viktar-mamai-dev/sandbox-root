package com.mamay.util;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class AppInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    public void initialize(@NonNull ConfigurableWebApplicationContext ctx) {
        try {
            addPropertySource("fileLocations", "/util.properties", ctx);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error loading context properties", e);
        }
    }

    private void addPropertySource(String beanName, String fileName, ConfigurableWebApplicationContext ctx)
            throws IOException {
        log.info("Loading properties " + fileName + " into property source " + beanName);
        try (InputStream input = AppInitializer.class.getResourceAsStream(fileName)) {
            Properties props = new Properties();
            props.load(input);
            PropertiesPropertySource propSource = new PropertiesPropertySource(beanName, props);
            ctx.getEnvironment().getPropertySources().addFirst(propSource);
        }
    }

}