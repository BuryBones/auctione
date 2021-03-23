package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.AbstractDto;
import com.epam.marketplace.exceptions.validity.ValidityException;

public interface LogicValidator<T extends AbstractDto> {

  void validate(T dto) throws ValidityException;

  ValidatorType getType();

}
