package com.example.externalized.configuration.bootstrap;

import com.example.externalized.configuration.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Locale;

@ImportResource("META-INF/spring/user-context.xml") //加载Spring 上下文XML文件
@EnableAutoConfiguration
public class XmlPlaceholderExternalizedConfigurationBootstrap {
    public static void main(String[] args) {


        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE) //非Web应用
                        .run(args);
        User user = context.getBean("user", User.class);
        System.err.println("用户对象:" + user);
        //关闭上下文
        context.close();

    }
}
