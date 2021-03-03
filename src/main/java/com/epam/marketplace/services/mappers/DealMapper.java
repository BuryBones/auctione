package com.epam.marketplace.services.mappers;

import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.services.dto.DealDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class DealMapper {

  private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
  private final DealCustomMapper customMapper = new DealCustomMapper();

  public DealDto getDtoFromEntity(Deal deal) {

    mapperFactory.classMap(Deal.class, DealDto.class)
        .customize(customMapper)
        .register();
    MapperFacade mapper = mapperFactory.getMapperFacade();
    DealDto result = mapper.map(deal,DealDto.class);
    return result;
  }

  public Deal getEntityFromDto(DealDto dealDto) {
    mapperFactory.classMap(DealDto.class, Deal.class);
    MapperFacade mapper = mapperFactory.getMapperFacade();

    return null;
  }

}
