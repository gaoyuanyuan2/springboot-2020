package com.example.externalized.configuration.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySources}{@link ApplicationContextInitializer} 实现
 */
public class ExtendPropertySourcesApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources =
                environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        source.put("user.id", "29");
        MapPropertySource propertySource = new MapPropertySource("from" +
                "-ApplicationContextInitializer", source);
        propertySources.addFirst(propertySource);
    }
}
