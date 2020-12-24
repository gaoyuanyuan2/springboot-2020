package com.example.externalized.configuration.bootstrap.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 外部化配置属性源{@link PropertySource} 顺序测试用例
 * https://docs.spring.io/spring-boot/docs/2.0.2
 * .RELEASE/reference/htmlsingle/#boot-features-external-config
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "user.id=9",
        locations = "classpath:META-INF/default.properties")
@SpringBootTest(
//        properties = "user.id=8",
        classes = {PropertySourceOrderTest.class
//                , PropertySourceOrderTest.MyConfig.class
        }, webEnvironment =
        SpringBootTest.WebEnvironment.NONE)
@org.springframework.context.annotation.PropertySource(name = "test-property" +
        "-source", value = "classpath:META-INF/test.properties")
@Configuration
public class PropertySourceOrderTest {

//    @org.springframework.context.annotation.PropertySource(name = "test-property" +
//            "-source", value = "classpath:META-INF/test.properties")
//    public static class MyConfig{
//
//    }

    @Value("${user.id}")
    private Long userId;

    @Test
    public void testUserId() {
        Assert.assertEquals(new Long(7L), userId);
    }

    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Test
    public void testEnvironment() {
        Assert.assertSame(applicationContext.getEnvironment(), environment);
    }

    @Test
    public void testPropertySources() {
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("PropertySource[名称:%s] : %s\n",
                    propertySource.getName(), propertySource);
            System.out.println();
        });
    }
}