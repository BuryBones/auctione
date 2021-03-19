package com.epam.marketplace.validation;

import com.epam.marketplace.dto.AbstractDto;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstraintsValidator {

  private final Validator validator;

  @Autowired
  public ConstraintsValidator(Validator validator) {
    this.validator = validator;
  }

  public <T extends AbstractDto> String validate(T dto) {
    Set<ConstraintViolation<T>> errors = validator.validate(dto);
    if (errors.isEmpty()) {
      return "ok";
    } else {
      StringBuilder stringBuilder = new StringBuilder("The following problems occurred:");
      errors.forEach(e -> stringBuilder.append("\r\n" + e.getMessage()));
      return stringBuilder.toString();
    }
  }
}
