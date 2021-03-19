package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.Bid_;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.entities.User_;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.BidDao;
import com.epam.marketplace.entities.Bid;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bidDao")
@Scope("prototype")
public class BidDaoImpl implements BidDao {

  @Override
  public List<Bid> findFromDate(LocalDateTime dateFrom) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Bid> criteriaQuery = criteriaBuilder.createQuery(Bid.class);

    Root<Bid> root = criteriaQuery.from(Bid.class);
    criteriaQuery.select(root)
        .where(criteriaBuilder.greaterThanOrEqualTo(root.get(Bid_.dateAndTime), dateFrom));

    Query<Bid> query = session.createQuery(criteriaQuery);
    List<Bid> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public Optional<Bid> findLastBidByDealId(int dealId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Bid> criteriaQuery = criteriaBuilder.createQuery(Bid.class);

    Root<Bid> root = criteriaQuery.from(Bid.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Bid_.DEAL),dealId));
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Bid_.dateAndTime)));

    Query<Bid> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<Bid> bids = query.getResultList();

    session.close();
    return bids.isEmpty() ? Optional.empty() : Optional.ofNullable(bids.get(0));
  }
}
