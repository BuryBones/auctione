package com.epam.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration

@ComponentScan("com.epam.marketplace.*")

public class ApplicationContextConfig {

  @Bean(name = "viewResolver")
  public ViewResolver getViewResolver() {
    FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
    System.out.println("Create Bean viewResolver");

    viewResolver.setCache(true);
    viewResolver.setPrefix("");
    viewResolver.setSuffix(".ftl");
    return viewResolver;
  }

  @Bean(name = "freemarkerConfig")
  public FreeMarkerConfigurer getFreemarkerConfig() {
    FreeMarkerConfigurer config = new FreeMarkerConfigurer();

    // Folder containing FreeMarker templates.
    config.setTemplateLoaderPath("/WEB-INF/views/");
//    config.setTemplateLoaderPath("/resources/views/");
    return config;
  }

}