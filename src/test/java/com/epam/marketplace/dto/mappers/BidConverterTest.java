package com.epam.marketplace.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.User;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BidConverterTest {

  private BoundMapperFacade<Bid, BidDto> bidConverter;
  private Bid bid;
  private BidDto bidDto;

  @BeforeEach
  private void setup() throws ParseException {
    BidirectionalConverter<Bid, BidDto> converter = new BidConverter();
    final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    converterFactory.registerConverter(converter);
    bidConverter = mapperFactory.getMapperFacade(Bid.class, BidDto.class);

    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm");

    User user = mock(User.class);
    when(user.getId()).thenReturn(14);

    Deal deal = mock(Deal.class);
    when(deal.getId()).thenReturn(11);

    bid = new Bid();
    bid.setId(17);
    bid.setOffer(new BigDecimal(750));
    bid.setDateAndTime(LocalDateTime.of(2021, Month.MAY, 11, 16, 51));
    bid.setUser(user);
    bid.setDeal(deal);

    bidDto = new BidDto();
    bidDto.setId(17);
    bidDto.setOffer(new BigDecimal(750));
    bidDto.setDateAndTime(format.parse("2021.05.11 16:51"));
    bidDto.setUserId(14);
    bidDto.setDealId(11);
  }

  @Test
  public void convertBidToDtoTest() {
    // when
    BidDto actual = bidConverter.map(bid);

    // then
    assertEquals(bidDto, actual);
  }

  @Test
  public void convertDtoToBidTest() {
    // when
    Bid actual = bidConverter.mapReverse(bidDto);

    // then
    assertEquals(bid.getOffer(), actual.getOffer());
    assertEquals(bid.getDateAndTime(), actual.getDateAndTime());
    assertEquals(bid.getDeal().getId(), actual.getDeal().getId());
    assertEquals(bid.getUser().getId(), actual.getUser().getId());
  }
}
