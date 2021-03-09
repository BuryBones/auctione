package com.epam.marketplace.services;

import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.dto.DealDto;
import com.epam.marketplace.dto.mappers.DealMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dealService")
public class DealService {

  @Autowired
  private DealDao dealDao;
  @Autowired
  private DealMapper dealMapper;

  public Long getAmount(String status) {
    return switch (status) {
      case "open" -> dealDao.findAmountByStatus(true);
      case "closed" -> dealDao.findAmountByStatus(false);
      case "all" -> dealDao.findAmount();
      default -> dealDao.findAmount();
    };
  }

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode, int currentPage) {
    // TODO: magic
    int pageSize = 5;
    Long totalAmount = getAmount(status);

    List<Deal> deals = switch (status) {
      case "open" -> dealDao.findAllFullWithLastBidByStatus(true, pageSize, currentPage);
      case "closed" -> dealDao.findAllFullWithLastBidByStatus(false, pageSize, currentPage);
      case "all" -> dealDao.findAllFullWithLastBid(pageSize, currentPage);
      default -> Collections.emptyList();
    };

    ArrayList<DealDto> result = new ArrayList<>(deals.size());
    for (Deal d: deals) {
      result.add(dealMapper.getDtoFromEntity(d));
    }
    return sort(result, sortBy, sortMode);
  }

  private List<DealDto> sort(ArrayList<DealDto> list, String sortBy, String sortMode) {
    boolean naturalOrder = sortMode.equals("asc");
    return CompareBy.valueOf(sortBy).sort(list,naturalOrder);
  }

  public boolean createAuction(DealDto newborn) {
    Deal newDeal = dealMapper.getEntityFromDto(newborn);
    try {
      dealDao.save(newDeal);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  enum CompareBy {
    id {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getId,Comparator.naturalOrder()))
          .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getId,Comparator.reverseOrder()))
          .collect(Collectors.toList());
        }
      }
    },
    seller {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getSeller,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getSeller,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    },
    item {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getItem,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getItem,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    },
    startDate {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getStartDate,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getStartDate,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    },
    startPrice {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getStartPrice,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getStartPrice,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    },
    lastBid {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getLastBid,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getLastBid,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    },
    stopDate {
      @Override
      public List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder) {
        if (naturalOrder) {
          return list.stream().sorted(Comparator.comparing(DealDto::getStopDate,Comparator.naturalOrder()))
              .collect(Collectors.toList());
        } else {
          return list.stream().sorted(Comparator.comparing(DealDto::getStopDate,Comparator.reverseOrder()))
              .collect(Collectors.toList());
        }
      }
    };

    public abstract List<DealDto> sort(ArrayList<DealDto> list, boolean naturalOrder);
  }

}
