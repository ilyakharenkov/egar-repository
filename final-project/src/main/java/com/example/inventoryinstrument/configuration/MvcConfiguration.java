package com.example.inventoryinstrument.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/storage/**", ResourceUtils.CLASSPATH_URL_PREFIX)
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/storage/")
                .addResourceLocations("classpath:/storage/alignment/**")
                .addResourceLocations("classpath:/storage/countersink/**")
                .addResourceLocations("classpath:");
    }
}
