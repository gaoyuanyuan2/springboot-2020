package com.example.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {
    public PropertiesHttpMessageConverter() {
        //设置支持的MediaType
        super(new MediaType("text", "properties"));

    }

    @Override
    protected void writeInternal(Properties properties, Type type,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        HttpHeaders httpHeaders = outputMessage.getHeaders();
        MediaType mediaType = httpHeaders.getContentType();
        //获取字符编码.
        Charset charset = mediaType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        OutputStream outputStream = outputMessage.getBody();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                charset);
        //Properties写入到字符输出流
        properties.store(writer, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz,
                                      HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        // 字符流->字符编码
        // 从请求头Content-Type 解析编码
        HttpHeaders httpHeaders = inputMessage.getHeaders();
        MediaType mediaType = httpHeaders.getContentType();
        //获取字符编码.
        Charset charset = mediaType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        // 字节流
        InputStream inputStream = inputMessage.getBody();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, charset);
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass,
                           HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
