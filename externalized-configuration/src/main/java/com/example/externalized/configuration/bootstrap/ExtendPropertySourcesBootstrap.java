package com.example.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySources;

/**
 * 扩展{@link PropertySources}引导类
 */
@EnableAutoConfiguration
@Configuration
@PropertySource(name = "from classpath:META-INF/default.properties", value =
        "classpath:META-INF/default.properties")
public class ExtendPropertySourcesBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                        .web(WebApplicationType.NONE) //非Web应用
                        .properties("user.id=99")
                        .run(of("--user.id=88"));// 4.Command line arguments.
        // 获取Environment 对象
        ConfigurableEnvironment environment = context.getEnvironment();
        System.err.printf("用户id : %d\n", environment.getProperty("user.id",
                Long.class));

        environment.getPropertySources().forEach(propertySource -> {
            System.err.printf("PropertySource[名称:%s] : %s\n",
                    propertySource.getName(), propertySource);
            System.out.println();
        });
        context.close();
    }

    private static <T> T[] of(T... args) {
        return args;
    }
}
