package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.AbstractDto;

public interface LogicValidator<T extends AbstractDto> {

  String validate(T dto);

}
