package com.example.externalized.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展{@link PropertySources}{@link ApplicationListener} 实现
 */
public class ExtendPropertySourcesEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment =  event.getEnvironment();
        MutablePropertySources propertySources =
                environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        source.put("user.id", "9");
        MapPropertySource propertySource = new MapPropertySource("from" +
                "-ApplicationEnvironmentPreparedEvent", source);
        propertySources.addFirst(propertySource);
    }
}
