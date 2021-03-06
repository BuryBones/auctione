package com.epam.marketplace.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(ApplicationContextConfig.class);

    // Dispatcher Servlet
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
        new DispatcherServlet(appContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    dispatcher.setInitParameter("contextClass", appContext.getClass().getName());

    servletContext.addListener(new ContextLoaderListener(appContext));
    registerCharacterEncodingFilter(servletContext);
  }

  private void registerCharacterEncodingFilter(ServletContext servletContext) {
    CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
    encodingFilter.setEncoding("UTF-8");
    encodingFilter.setForceEncoding(true);
    FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", encodingFilter);
    characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
  }
}
