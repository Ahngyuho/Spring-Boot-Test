package com.example.demo.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MyStringMessageConverter myStringMessageConverter;

    public WebConfig(MyStringMessageConverter myStringMessageConverter) {
        this.myStringMessageConverter = myStringMessageConverter;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 최우선으로
        converters.add(0, myStringMessageConverter);
    }
}
