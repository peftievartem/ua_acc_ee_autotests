package com.self.utils.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigFileReader {
    private static Logger LOG = LoggerFactory.getLogger(ConfigFileReader.class);
    private final Properties properties = new Properties();
    URL url = createCukesPropertyFileUrl(ConfigFileReader.class.getClassLoader());

    public ConfigFileReader() {
        try {
            this.properties.load(this.url.openStream());
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public String getBaseUrl() {
        String url = this.properties.getProperty("baseUrl");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("Base Url is not specified in the cukes.properties file.");
        }
    }

    public String getApplicationsUrl() {
        String url = this.properties.getProperty("appsUrl");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("Base Url is not specified in the cukes.properties file.");
        }
    }

    public String getDatabaseName() {
        String databaseName = this.properties.getProperty("databaseName");
        if (databaseName != null) {
            return databaseName;
        } else {
            throw new RuntimeException("Database name is not specified in the cukes.properties file.");
        }
    }

    public String getUserNameSuperAdmin() {
        String userName = this.properties.getProperty("userNameSuperAdmin");
        if (userName != null) {
            return userName;
        } else {
            throw new RuntimeException("UserName is not specified in the cukes.properties file.");
        }
    }

    public String getUserNameLocalAdmin() {
        String userName = this.properties.getProperty("userNameLocalAdmin");
        if (userName != null) {
            return userName;
        } else {
            throw new RuntimeException("UserName is not specified in the cukes.properties file.");
        }
    }

    public String getPassword() {
        String password = this.properties.getProperty("password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("Password is not specified in the cukes.properties file.");
        }
    }

    public String getDriverPath() {
        String path = this.properties.getProperty("driverPath");
        if (path != null) {
            return path;
        } else {
            throw new RuntimeException("Driver path is not specified in the cukes.properties file");
        }
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = this.properties.getProperty("windowMaximize");
        return windowSize == null || Boolean.parseBoolean(windowSize);
    }

    public int getExplicitWaitSec() {
        String explicitWaitSec = this.properties.getProperty("explicitWaitTimeout");
        if (explicitWaitSec == null) {
            LOG.warn("!!!explicitWaitTimeout property is not found in cukes.properties file., hence using default timeout as 10 seconds");
            return 10;
        } else if (Integer.parseInt(explicitWaitSec) < 101) {
            return Integer.parseInt(explicitWaitSec);
        } else {
            throw new RuntimeException("Explicit wait is too big in the cukes.properties file. Please specify a lower value.");
        }
    }

    public String getPropertyValue(String name) {
        String value = this.properties.getProperty(name);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException(name + " property is not specified in the cukes.properties file.");
        }
    }

    public static URL createCukesPropertyFileUrl(ClassLoader classLoader) {
        String cukesProfile = System.getProperty("cukes.profile");
        String propertiesFileName = cukesProfile != null && !cukesProfile.isEmpty() ? "cukes-" + cukesProfile + ".properties" : "cukes.properties";
        return classLoader.getResource(propertiesFileName);
    }

    public int getAjaxTimeout() {
        String ajaxTimeout = this.properties.getProperty("ajaxTimeout");
        if (ajaxTimeout == null) {
            LOG.warn("!!!ajaxTimeout property is not found in cukes.properties file., hence using default timeout as 15 seconds");
            return 15;
        } else if (Integer.parseInt(ajaxTimeout) < 101) {
            return Integer.parseInt(ajaxTimeout);
        } else {
            throw new RuntimeException("ajaxTimeout is too big in the cukes.properties file. Please specify a lower value.");
        }
    }

    public int getRetryTimeout() {
        String retryTimeout = this.properties.getProperty("retryTimeout");
        if (retryTimeout == null) {
            LOG.warn("!!!retryTimeout property is not found in cukes.properties file., hence using default timeout as 1 second");
            return 1;
        } else if (Integer.parseInt(retryTimeout) < 101) {
            return Integer.parseInt(retryTimeout);
        } else {
            throw new RuntimeException("retryTimeout is too big in the cukes.properties file. Please specify a lower value.");
        }
    }
}
