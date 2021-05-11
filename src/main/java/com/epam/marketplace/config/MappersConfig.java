package com.epam.marketplace.config;

import com.epam.marketplace.dto.BidDto;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto.UserDto;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import java.util.List;
import javax.annotation.PostConstruct;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

  @Autowired
  private List<? extends BidirectionalConverter> orikaConverters;

  @PostConstruct
  public void init() {
    ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    for (BidirectionalConverter converter : orikaConverters) {
      converterFactory.registerConverter(converter);
    }
  }

  @Bean
  public BoundMapperFacade<User, UserDto> getUserConverter() {
    return mapperFactory.getMapperFacade(User.class, UserDto.class);
  }

  @Bean
  public BoundMapperFacade<Item, ItemDto> getItemConverter() {
    return mapperFactory.getMapperFacade(Item.class, ItemDto.class);
  }

  @Bean
  public BoundMapperFacade<Deal, DealDto> getDealConverter() {
    return mapperFactory.getMapperFacade(Deal.class, DealDto.class);
  }

  @Bean
  public BoundMapperFacade<Bid, BidDto> getBidConverter() {
    return mapperFactory.getMapperFacade(Bid.class, BidDto.class);
  }
}
