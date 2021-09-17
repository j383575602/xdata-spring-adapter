package com.demo.example


import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import top.xcore.springboot.adapter.XDataConverter

@Configuration
class XDataConfiguration : WebMvcConfigurer {
    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.extendMessageConverters(converters)
        println("========extendMessageConverters is ccreateed");
        converters.add(XDataConverter());
    }
}