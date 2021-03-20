package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.mappers.CommonMapper;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.validation.ConstraintsValidator;
import com.epam.marketplace.OperationResult;
import com.epam.marketplace.validation.logic.BidLogicValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bidService")
public class BidService {

  private final BidDao bidDao;
  private final CommonMapper mapper;
  private final ConstraintsValidator constraintsValidator;
  private final BidLogicValidator bidLogicValidator;

  @Autowired
  public BidService(BidDao bidDao, CommonMapper mapper,
      ConstraintsValidator constraintsValidator,
      BidLogicValidator bidLogicValidator) {
    this.bidDao = bidDao;
    this.mapper = mapper;
    this.constraintsValidator = constraintsValidator;
    this.bidLogicValidator = bidLogicValidator;
  }

  public OperationResult createBid(BidDto newBorn) {
    OperationResult validationResult = validate(newBorn);
    if (validationResult.getResult()) {
      Bid newBid = mapper.getEntityFromDto(newBorn);
      return bidDao.save(newBid);
    } else {
      return validationResult;
    }
  }

  // TODO: may be separated into some services-common interface?
  public OperationResult validate(BidDto bidDto) {
    OperationResult constraintsValidationResult = constraintsValidator.validate(bidDto);
    if (constraintsValidationResult.getResult()) {
      return bidLogicValidator.validate(bidDto);
    } else {
      return constraintsValidationResult;
    }
  }
}
