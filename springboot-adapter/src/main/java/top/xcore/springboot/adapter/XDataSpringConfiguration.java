package top.xcore.springboot.adapter;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * springboot adapter
 */
@Configuration
public class XDataSpringConfiguration implements WebMvcConfigurer {
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("========extendMessageConverters is created");
        converters.add(0,new XDataConverter());
    }
}