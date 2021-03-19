package com.epam.marketplace.dto.mappers;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("commonMapper")
@Scope("prototype")
public class CommonMapper {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

  @Autowired
  private List<? extends BidirectionalConverter> orikaConverters;

  @PostConstruct
  public void init() {
    ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    for (BidirectionalConverter converter: orikaConverters) {
      converterFactory.registerConverter(converter);
    }
  }

  // TODO: generify?
  public BidDto getDtoFromEntity(Bid entity) {
    BoundMapperFacade<Bid,BidDto> mapper = mapperFactory.getMapperFacade(Bid.class,BidDto.class);
    return mapper.map(entity);
  }

  public Bid getEntityFromDto(BidDto dto) {
    BoundMapperFacade<Bid,BidDto> mapper = mapperFactory.getMapperFacade(Bid.class,BidDto.class);
    return mapper.mapReverse(dto);
  }

  public DealDto getDtoFromEntity(Deal entity) {
    BoundMapperFacade<Deal,DealDto> mapper = mapperFactory.getMapperFacade(Deal.class,DealDto.class);
    return mapper.map(entity);
  }

  public Deal getEntityFromDto(DealDto dto) {
    BoundMapperFacade<Deal,DealDto> mapper = mapperFactory.getMapperFacade(Deal.class,DealDto.class);
    return mapper.mapReverse(dto);
  }

  public ItemDto getDtoFromEntity(Item entity) {
    BoundMapperFacade<Item,ItemDto> mapper = mapperFactory.getMapperFacade(Item.class,ItemDto.class);
    return mapper.map(entity);
  }

  public Item getEntityFromDto(ItemDto dto) {
    BoundMapperFacade<Item,ItemDto> mapper = mapperFactory.getMapperFacade(Item.class,ItemDto.class);
    return mapper.mapReverse(dto);
  }

  public UserDto getDtoFromEntity(User entity) {
    BoundMapperFacade<User,UserDto> mapper = mapperFactory.getMapperFacade(User.class,UserDto.class);
    return mapper.map(entity);
  }

  public User getEntityFromDto(UserDto dto) {
    BoundMapperFacade<User,UserDto> mapper = mapperFactory.getMapperFacade(User.class,UserDto.class);
    return mapper.mapReverse(dto);
  }
}
