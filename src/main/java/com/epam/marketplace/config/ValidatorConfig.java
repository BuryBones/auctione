package com.epam.marketplace.config;

import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.ValidatorType;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

  private final Logger logger = Logger.getLogger("application");

  @Autowired
  private List<LogicValidator<? extends Dto>> validators;

  @Autowired
  private ConfigurableBeanFactory beanFactory;

  @PostConstruct
  private void registerValidators() {
    for (ValidatorType type : ValidatorType.values()) {
      List<LogicValidator<? extends Dto>> list = validators.stream()
          .filter(e -> e.getType().equals(type))
          .collect(Collectors.toUnmodifiableList());
      beanFactory.registerSingleton(type.getName(), list);
      logger.info("Registered: " + type.getName() + ": " + list.size());
    }
  }
}
