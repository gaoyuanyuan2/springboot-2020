package com.example.method.support;

import com.example.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Properties;

public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
//        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
//        HttpServletRequest request = servletWebRequest.getRequest();
//
//        ServletInputStream inputStream = request.getInputStream();
//        String contentType = request.getHeader("Content-Type");
//        MediaType mediaType = MediaType.parseMediaType(contentType);
//        //获取字符编码.
//        Charset charset = mediaType.getCharset();
//        charset = charset == null ? Charset.forName("UTF-8") : charset;
//
//        InputStreamReader inputStreamReader =
//                new InputStreamReader(inputStream, charset);
//        Properties properties = new Properties();
//        properties.load(inputStreamReader);
//        return properties;
        // 复用改进
        PropertiesHttpMessageConverter converter =
                new PropertiesHttpMessageConverter();
        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        HttpInputMessage message =
                new ServletServerHttpRequest(servletWebRequest.getRequest());
        return converter.read(null, null, message);
    }
}
