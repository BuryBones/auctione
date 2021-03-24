package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.exceptions.validity.ValidityException;

public interface LogicValidator<T extends Dto> {

  void validate(T dto) throws ValidityException;

  ValidatorType getType();

}
