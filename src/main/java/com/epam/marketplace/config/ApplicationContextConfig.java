package com.epam.marketplace.config;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableScheduling
@ComponentScan("com.epam.marketplace.*")
public class ApplicationContextConfig {

  @Bean(name = "viewResolver")
  public ViewResolver getViewResolver() {
    FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

    viewResolver.setCache(true);
    viewResolver.setPrefix("");
    viewResolver.setSuffix(".ftl");
    viewResolver.setContentType("text/html;charset=UTF-8");
    return viewResolver;
  }

  @Bean(name = "freemarkerConfig")
  public FreeMarkerConfigurer getFreemarkerConfig() {
    FreeMarkerConfigurer config = new FreeMarkerConfigurer();

    // Folder containing FreeMarker templates.
    config.setTemplateLoaderPath("/WEB-INF/views/");
    config.setDefaultEncoding("UTF-8");
    return config;
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @PostConstruct
  private void initLogger() {
    Logger logger = Logger.getLogger("application");
    logger.setLevel(Level.ALL);
    logger.addHandler(new ConsoleHandler());
  }
}