package com.epam.marketplace.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DealConverterTest {

  private BoundMapperFacade<Deal, DealDto> dealConverter;
  private Deal deal;
  private DealDto dealDto;

  @BeforeEach
  private void setup() throws ParseException {
    BidirectionalConverter<Deal, DealDto> converter = new DealConverter();
    final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    converterFactory.registerConverter(converter);
    dealConverter = mapperFactory.getMapperFacade(Deal.class, DealDto.class);

    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm");

    User user = mock(User.class);
    lenient().when(user.getFirstName()).thenReturn("TestFirstName");
    lenient().when(user.getLastName()).thenReturn("TestLastName");
    lenient().when(user.getId()).thenReturn(5);

    Item item = mock(Item.class);
    lenient().when(item.getId()).thenReturn(8);
    lenient().when(item.getName()).thenReturn("TestItemName");
    lenient().when(item.getDescript()).thenReturn("TestItemDescription");

    deal = new Deal();
    deal.setId(11);
    deal.setOpenTime(LocalDateTime.of(2021, Month.MAY, 10, 23, 15));
    deal.setInitPrice(new BigDecimal(980));
    deal.setCloseTime(LocalDateTime.of(2021, Month.DECEMBER, 30, 0, 1));
    deal.setStatus(true);
    deal.setUser(user);
    deal.setItem(item);

    dealDto = new DealDto();
    dealDto.setId(11);
    dealDto.setStartPrice(new BigDecimal(980));
    dealDto.setStartDate(format.parse("2021.05.10 23:15"));
    dealDto.setStopDate(format.parse("2021.12.30 00:01"));
    dealDto.setStatus(true);
    dealDto.setSellerId(5);
    dealDto.setSeller("TestLastName" + " " + "TestFirstName");
    dealDto.setItemId(8);
    dealDto.setLastBid(new BigDecimal(0));
  }

  @Test
  @DisplayName("Deal to DealDto test")
  public void convertDealToDtoTest() {
    // when
    DealDto actual = dealConverter.map(deal);

    // then
    assertEquals(dealDto, actual);
    assertEquals(dealDto.getSeller(), actual.getSeller());
    assertEquals(dealDto.getStatus(), actual.getStatus());
    assertEquals(dealDto.getStartPrice(), actual.getStartPrice());
    assertEquals(dealDto.getLastBid(), actual.getLastBid());
    assertEquals(dealDto.getStartDate(), actual.getStartDate());
    assertEquals(dealDto.getStopDate(), actual.getStopDate());
  }

  @Test
  @DisplayName("DealDto to Deal test")
  public void convertDtoToDealTest() {
    // when
    Deal actual = dealConverter.mapReverse(dealDto);

    // then
    assertEquals(deal.getInitPrice(), actual.getInitPrice());
    assertEquals(deal.getOpenTime(), actual.getOpenTime());
    assertEquals(deal.getCloseTime(), actual.getCloseTime());
    assertEquals(deal.getStatus(), actual.getStatus());
    assertEquals(deal.getUser().getId(), actual.getUser().getId());
  }

  @Test
  @DisplayName("Convert a Deal with several Bids, setting the highest Bid as 'lastBid' in DealDto")
  public void convertDealToDtoWithLastBidTest() {
    // given
    Bid lowBid = mock(Bid.class);
    when(lowBid.getOffer()).thenReturn(new BigDecimal(1010));

    Bid middleBid = mock(Bid.class);
    when(middleBid.getOffer()).thenReturn(new BigDecimal(1030));

    Bid topBid = mock(Bid.class);
    when(topBid.getOffer()).thenReturn(new BigDecimal(1090));

    deal.addBid(lowBid);
    deal.addBid(middleBid);
    deal.addBid(topBid);

    // when
    DealDto actual = dealConverter.map(deal);

    // then
    assertEquals(topBid.getOffer(), actual.getLastBid());
  }
}
