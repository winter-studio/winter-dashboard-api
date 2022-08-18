package cn.wintersoft.dashboard.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class LocaleMessage {
    private static final String defaultLocale = "en";
    private static final Map<String, Properties> messages = new HashMap<>();

    static {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void init() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("messages");
        File[] files = classPathResource.getFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".properties")) {
                    String locale = fileName.substring(0, fileName.length() - ".properties".length());
                    Properties properties = new Properties();
                    properties.load(file.toURI().toURL().openStream());
                    messages.put(locale, properties);
                }
            }
        }
    }

    public static String get(String key) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String property = messages.get(currentLocale()).getProperty(key);
        return property == null ? key : property;
    }

    public static String currentLocale() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String locale = Optional.ofNullable(ra).map(ServletRequestAttributes::getRequest)
                                .map(r -> r.getHeader("Accept-Language")).orElse(defaultLocale);
        if (messages.containsKey(locale)) {
            return locale;
        }
        return defaultLocale;
    }

}
