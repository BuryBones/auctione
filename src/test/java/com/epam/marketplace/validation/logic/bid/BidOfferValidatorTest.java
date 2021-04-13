package com.epam.marketplace.validation.logic.bid;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.exceptions.validity.ValidityException;
import java.math.BigDecimal;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BidOfferValidatorTest {

  @Mock
  private BidDao bidDao;

  @InjectMocks
  @Resource
  private BidOfferValidator bidOfferValidator;

  private BidDto bidDto;
  private Bid lastBid;

  @BeforeEach
  public void setup() {
    bidDto = new BidDto();
    bidDto.setOffer(BigDecimal.valueOf(100));
    bidDto.setDealId(5);
    lastBid = new Bid();
    Mockito.when(bidDao.findLastBidByDealId(5)).thenReturn(Optional.of(lastBid));
  }

  @Test
  public void equalOfferTest() {
    lastBid.setOffer(BigDecimal.valueOf(100));

    assertThrows(ValidityException.class, () -> {
      bidOfferValidator.validate(bidDto);
    });
  }

  @Test
  public void lowerOfferTest() {
    lastBid.setOffer(BigDecimal.valueOf(101));

    assertThrows(ValidityException.class, () -> {
      bidOfferValidator.validate(bidDto);
    });
  }
}
