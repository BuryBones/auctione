package com.epam.marketplace.config;

import com.epam.marketplace.dto.AbstractDto;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.ValidatorType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ValidatorConfig {

  @Autowired
  private List<LogicValidator<? extends AbstractDto>> validators;

  @Bean("validators")
  @Scope("prototype")
  public List<LogicValidator<? extends AbstractDto>> getValidators(ValidatorType type) {
    return validators.stream()
        .filter(e -> e.getType().equals(type))
        .collect(Collectors.toList());
  }
}
