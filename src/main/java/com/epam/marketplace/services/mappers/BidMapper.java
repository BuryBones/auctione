package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.services.dto.BidDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component("bidMapper")
public class BidMapper {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
  private final BidCustomMapper customMapper = new BidCustomMapper();

  public BidDto getDtoFromEntity(Bid bid) {
    mapperFactory.classMap(Bid.class, BidDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(bid,BidDto.class);
  }

  public Bid getEntityFromDto(BidDto bidDto) {
    mapperFactory.classMap(Bid.class, BidDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    return mapper.map(bidDto, Bid.class);
  }
}
