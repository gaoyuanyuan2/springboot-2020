package com.example.spring.web.servlet.support;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring Web MIC自动装配默认实现
 */
@ComponentScan(basePackages = "com.example.spring.web.servlet.controller")
@Configuration
public class DefaultAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {// web.xml
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {// DispatcherServlet
        return new Class[]{getClass()};//当前类
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
