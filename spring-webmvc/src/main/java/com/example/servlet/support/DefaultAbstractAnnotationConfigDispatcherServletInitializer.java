package com.example.servlet.support;

import com.example.config.DispatcherServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring Web MIC自动装配默认实现
 */
public class DefaultAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {// web.xml
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {// DispatcherServlet
        return new Class[]{DispatcherServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
