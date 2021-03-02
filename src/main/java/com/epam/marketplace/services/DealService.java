package com.epam.marketplace.services;

import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.dao.impl.BidDaoImpl;
import com.epam.marketplace.dao.impl.DealDaoImpl;
import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Deal;
import com.epam.marketplace.services.dto.DealDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service("defaultService")
public class DealService {

  public List<DealDto> getAuctions(String status, String sortBy, String sortMode) {
    DealDao dealDao = new DealDaoImpl();
    BidDao bidDao = new BidDaoImpl();
    // TODO: transfer directly to the mapper?
    List<Deal> deals = switch (status) {
      case "open" -> dealDao.findAllFullByStatus(true);
      case "closed" -> dealDao.findAllFullByStatus(false);
      case "all" -> dealDao.findAllFull();
      default -> Collections.emptyList();
    };
    ArrayList<DealDto> result = new ArrayList<>();
    for (Deal d: deals) {
      DealDto row = new DealDto();
      row.setId(d.getId());
      row.setSeller(d.getUser().getFirstName() + " " + d.getUser().getLastName());
      row.setItem(d.getItem().getName());
      row.setInfo(d.getItem().getDescript());
      row.setStartDate(d.getOpenTime());
      row.setStartPrice(d.getInitPrice());
      Optional<Bid> optionalBid = bidDao.findLastBidByDealId(d.getId());
      if (optionalBid.isPresent()) {
        row.setLastBid(optionalBid.get().getOffer());
      } else {
        row.setLastBid(new BigDecimal("0"));
      }
      row.setStopDate(d.getCloseTime());
      row.setStatus(d.getStatus());
      result.add(row);
    }
    return sort(result, sortBy, sortMode);
  }

  private List<DealDto> sort(ArrayList<DealDto> list, String sortBy, String sortMode) {
    boolean naturalOrder = sortMode.equals("asc");
    return CompareBy.valueOf(sortBy).sort(list,naturalOrder);
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
