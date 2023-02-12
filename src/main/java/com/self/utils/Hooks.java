package com.self.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Hooks {
    public static Logger LOG = LoggerFactory.getLogger(Hooks.class);

    public static Properties loadProperties() {
        Properties p = new Properties();
        InputStream passwordsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("password.properties");
        try {
            p.load(passwordsStream);
        } catch (IOException e) {
            LOG.warn(e.getMessage());
        }
        return p;
    }
}
