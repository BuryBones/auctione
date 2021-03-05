package com.epam.marketplace.services;

import com.epam.marketplace.services.dto.BidDto;
import com.epam.marketplace.services.dto.DealDto;
import com.epam.marketplace.services.dto.ItemDto;
import com.epam.marketplace.services.dto.UserDto;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("dtoAssembler")
@Scope("prototype")
public class DtoAssembler {

  public UserDto newUser(String login, String password, String firstName, String lastName) {
    UserDto result = new UserDto();
    result.setLogin(login);
    result.setPassword(password);
    result.setFirstName(firstName);
    result.setLastName(lastName);
    result.addRole("REGULAR_USER");
    return result;
  }

  public ItemDto newItem(int userId, String name, String description) {
    ItemDto result = new ItemDto();
    result.setUserId(userId);
    result.setName(name);

    return result;
  }

  public DealDto newDeal(int userId, int itemId, String initPrice, String stopDate, String stopTime)
      throws ParseException {
    DealDto result = new DealDto();
    result.setSellerId(userId);
    result.setItemId(itemId);
    result.setStartPrice(new BigDecimal(initPrice));
    result.setStartDate(new Date());
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    result.setStopDate(formatter.parse(stopDate + " " + stopTime));
    return result;
  }

  public BidDto newBid(int userId, int dealId, String offer) {
    BidDto result = new BidDto();
    result.setUserId(userId);
    result.setDealId(dealId);
    result.setOffer(new BigDecimal(offer));
    result.setDateAndTime(new Date());
    return result;
  }

}
