package com.example.externalized.configuration.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.context.config.ConfigFileApplicationListener.DEFAULT_ORDER;

/**
 * 扩展{@link PropertySources}{@link EnvironmentPostProcessor} 实现
 */
public class ExtendPropertySourcesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        MutablePropertySources propertySources =
                environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        source.put("user.id", "19");
        MapPropertySource propertySource = new MapPropertySource("from" +
                "-EnvironmentPostProcessor", source);
        propertySources.addFirst(propertySource);
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER - 1;
    }
}
