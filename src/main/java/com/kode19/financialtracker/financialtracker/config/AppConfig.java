package com.kode19.financialtracker.financialtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {

    var source = new ResourceBundleMessageSource();
    source.setBasenames("messages/label");
    source.setUseCodeAsDefaultMessage(true);

    return source;
  }
}