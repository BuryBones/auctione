package com.epam.marketplace.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.dto.Dto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.validation.logic.LogicValidator;
import com.epam.marketplace.validation.logic.bid.BidOfferValidator;
import com.epam.marketplace.validation.logic.bid.BidOwnerValidator;
import com.epam.marketplace.validation.logic.bid.BidTimestampValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.Resource;
import ma.glasnost.orika.BoundMapperFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BidServiceTest {

  @Mock
  private BidDao bidDao;
  @Mock
  private BoundMapperFacade<Bid, BidDto> mapper;
  @Mock
  private UserService userService;
  @Spy
  private ArrayList<LogicValidator<? extends Dto>> validators;

  @InjectMocks
  @Resource
  private BidService bidService;

  private BidDto newBorn;

  @BeforeEach
  public void setup() {
    newBorn = new BidDto();
    newBorn.setDealId(10);
    newBorn.setOffer(BigDecimal.valueOf(100));

    BidOfferValidator bidOfferValidator = Mockito.mock(BidOfferValidator.class);
    BidOwnerValidator bidOwnerValidator = Mockito.mock(BidOwnerValidator.class);
    BidTimestampValidator bidTimestampValidator = Mockito.mock(BidTimestampValidator.class);
    validators.add(bidOfferValidator);
    validators.add(bidOwnerValidator);
    validators.add(bidTimestampValidator);
  }

  @Test
  public void setupTest() {
    assertNotNull(bidService);
    assertNotNull(bidDao);
    assertNotNull(mapper);
    assertNotNull(userService);
    assertNotNull(newBorn);
  }

  @Test
  public void setUserIdTest() {
    // when
    Mockito.when(userService.getCurrentUserId()).thenReturn(2);
    bidService.createBid(newBorn);

    // then
    assertEquals(2, newBorn.getUserId());
  }
}
