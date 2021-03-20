package com.epam.marketplace.validation.logic;

import com.epam.marketplace.dto.AbstractDto;
import com.epam.marketplace.OperationResult;

public interface LogicValidator<T extends AbstractDto> {

  OperationResult validate(T dto);

}
