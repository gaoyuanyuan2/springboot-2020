package com.example.externalized.configuration.bootstrap;

import com.example.externalized.configuration.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

@EnableAutoConfiguration
public class ValueAnnotationBootstrap
        implements EnvironmentAware
    , BeanFactoryAware
{

    @Autowired
    private Environment environment;

    @Override
    public void setEnvironment (Environment environment) {
        this.environment=environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.environment = beanFactory.getBean(ENVIRONMENT_BEAN_NAME
                ,Environment.class);
    }

    @Bean
    public User user2(Environment environment) {
        Long id = environment.getRequiredProperty("user.id", Long.class);
        String name = environment.getRequiredProperty("user.name");
        Integer age = environment.getProperty("user.age", Integer.class,
                environment.getProperty("my.user.age", Integer.class, 32));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Bean
    public User user(@Value("${user.id}") Long id,
                     @Value("${user.name}") String name,
                     @Value("${user.age}") Integer age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ValueAnnotationBootstrap.class)
                        .web(WebApplicationType.NONE) //非Web应用
                        .run(args);
        User user = context.getBean("user", User.class);
        System.err.println("用户对象:" + user);
        User user2 = context.getBean("user2", User.class);
        System.err.println("用户对象2:" + user2);
        //关闭上下文
        context.close();
    }


}
