package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Bid_;
import com.epam.marketplace.entities.Deal_;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.entities.Deal;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component("dealDao")
public class DealDaoImpl implements DealDao {

  @Override
  public List<Deal> findByStatus(boolean status) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Deal> criteriaQuery = criteriaBuilder.createQuery(Deal.class);

    Root<Deal> root = criteriaQuery.from(Deal.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Deal_.status),status));

    Query<Deal> query = session.createQuery(criteriaQuery);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> findFromDate(LocalDateTime dateFrom) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Deal> criteriaQuery = criteriaBuilder.createQuery(Deal.class);

    Root<Deal> root = criteriaQuery.from(Deal.class);
    criteriaQuery.select(root)
        .where(criteriaBuilder.greaterThanOrEqualTo(root.get("openTime"), dateFrom));

    Query<Deal> query = session.createQuery(criteriaQuery);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> findAllFull() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Deal> criteriaQuery = criteriaBuilder.createQuery(Deal.class);

    Root<Deal> root = criteriaQuery.from(Deal.class);
    root.fetch(Deal_.user, JoinType.LEFT);
    root.fetch(Deal_.item, JoinType.LEFT);
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Deal_.id)));

    Query<Deal> query = session.createQuery(criteriaQuery);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> findAllFullByStatus(boolean status) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Deal> criteriaQuery = criteriaBuilder.createQuery(Deal.class);

    Root<Deal> root = criteriaQuery.from(Deal.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Deal_.status),status));
    root.fetch(Deal_.user, JoinType.LEFT);
    root.fetch(Deal_.item, JoinType.LEFT);
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Deal_.id)));

    Query<Deal> query = session.createQuery(criteriaQuery);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> findAllFullWithLastBid() {

    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Deal> cq = cb.createQuery(Deal.class);

    Root<Deal> root = cq.from(Deal.class);
    root.fetch(Deal_.user, JoinType.LEFT);
    root.fetch(Deal_.item, JoinType.LEFT);
    cq.orderBy(cb.asc(root.get(Deal_.id)));

    Subquery<BigDecimal> sub = cq.subquery(BigDecimal.class);
    Root subRoot = sub.from(Bid.class);
    SetJoin<Deal, Bid> subBids = subRoot.join(Deal_.bids);
    sub.select(cb.max(subRoot.get(Bid_.offer)));
    sub.where(cb.equal(root.get(Deal_.id), subBids.get(Bid_.deal)));

    Query<Deal> query = session.createQuery(cq);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> findAllFullWithLastBidByStatus(boolean status) {

    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Deal> cq = cb.createQuery(Deal.class);

    Root<Deal> root = cq.from(Deal.class);
    root.fetch(Deal_.user, JoinType.LEFT);
    root.fetch(Deal_.item, JoinType.LEFT);
    if (status) {
      cq.where(cb.greaterThan(root.get(Deal_.closeTime),LocalDateTime.now()));
    } else {
      cq.where(cb.lessThan(root.get(Deal_.closeTime),LocalDateTime.now()));
    }
    cq.orderBy(cb.asc(root.get(Deal_.id)));

    Subquery<BigDecimal> sub = cq.subquery(BigDecimal.class);
    Root subRoot = sub.from(Bid.class);
    SetJoin<Deal, Bid> subBids = subRoot.join(Deal_.bids);
    sub.select(cb.max(subRoot.get(Bid_.offer)));
    sub.where(cb.equal(root.get(Deal_.id), subBids.get(Bid_.deal)));

    Query<Deal> query = session.createQuery(cq);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

}
