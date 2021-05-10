package com.epam.marketplace.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.User;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemConverterTest {

  private BoundMapperFacade<Item, ItemDto> itemConverter;
  private Item item;
  private ItemDto itemDto;

  @BeforeEach
  private void setup() {
    BidirectionalConverter<Item, ItemDto> converter = new ItemConverter();
    final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
    converterFactory.registerConverter(converter);
    itemConverter = mapperFactory.getMapperFacade(Item.class, ItemDto.class);

    User user = Mockito.mock(User.class);
    Mockito.lenient().when(user.getId()).thenReturn(5);

    item = new Item();
    item.setId(3);
    item.setName("testName");
    item.setDescript("testDesc");
    item.setUser(user);

    itemDto = new ItemDto();
    itemDto.setId(3);
    itemDto.setName("testName");
    itemDto.setDescription("testDesc");
    itemDto.setUserId(5);
  }

  @Test
  @DisplayName("Item to ItemDTO test")
  public void convertItemToDtoTest() {
    // when
    ItemDto actual = itemConverter.map(item);

    // then
    assertEquals(itemDto, actual);
    assertEquals(itemDto.getDescription(), actual.getDescription());
  }

  @Test
  @DisplayName("ItemDTO to Item test")
  public void convertDtoToItemTest() {
    // when
    Item actual = itemConverter.mapReverse(itemDto);

    // then
    assertEquals(item.getName(), actual.getName());
    assertEquals(item.getDescript(), actual.getDescript());
    assertEquals(item.getUser().getId(), actual.getUser().getId());
  }

  @Test
  @DisplayName("ItemDTO with null description should be converted to Item with default description")
  public void convertDtoToItemDefaultDescriptionTest() {
    // given
    itemDto = new ItemDto();
    itemDto.setId(33);
    itemDto.setName("doesn't matter");
    itemDto.setUserId(19);

    // when
    Item actual = itemConverter.mapReverse(itemDto);

    // then
    assertEquals(actual.getDescript(), "No Description");
  }
}
