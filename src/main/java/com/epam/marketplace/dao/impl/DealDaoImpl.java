package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.Bid;
import com.epam.marketplace.entities.Bid_;
import com.epam.marketplace.entities.Deal_;
import com.epam.marketplace.entities.Item;
import com.epam.marketplace.entities.Item_;
import com.epam.marketplace.entities.User;
import com.epam.marketplace.entities.User_;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.entities.Deal;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("dealDao")
@Scope("prototype")
public class DealDaoImpl implements DealDao {

  private HashMap<String, Function<From,Expression>> compareBy = new HashMap<>();
  {
    compareBy.put("id",         (root) -> root.get(Deal_.id));
    compareBy.put("seller",     (root) -> root.get(User_.lastName));
    compareBy.put("item",       (root) -> root.get(Item_.name));
    compareBy.put("startDate",  (root) -> root.get(Deal_.openTime));
    compareBy.put("startPrice", (root) -> root.get(Deal_.initPrice));
    compareBy.put("lastBid",    (root) -> root.get(Bid_.offer));  // doesn't work
    compareBy.put("stopDate",   (root) -> root.get(Deal_.closeTime));
  }

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
  public List<Deal> findAllFullWithLastBidByStatus(String status, int pageSize, int currentPage, String sortBy, boolean order) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Deal> cq = cb.createQuery(Deal.class);

    Root<Deal> root = cq.from(Deal.class);
    Join<Deal, User> userJoin = (Join<Deal, User>) root.fetch(Deal_.user);
    Join<Deal, Item> itemJoin = (Join<Deal, Item>) root.fetch(Deal_.item);

    switch (status) {
      case "open":
        cq.where(cb.greaterThan(root.get(Deal_.closeTime),LocalDateTime.now()));
        break;
      case "closed":
        cq.where(cb.lessThan(root.get(Deal_.closeTime),LocalDateTime.now()));
        break;
      case "all":
      default: // do nothing
    }
    Subquery<BigDecimal> sub = cq.subquery(BigDecimal.class);
    Root subRoot = sub.from(Bid.class);
    Join<Deal, Bid> subBids = subRoot.join(Deal_.bids);
    sub.select(cb.max(subRoot.get(Bid_.offer)));
    sub.where(cb.equal(root.get(Deal_.id), subBids.get(Bid_.deal)));
    cq.select(root);

    if (order) {
      switch (sortBy) {
        case "seller":
          cq.orderBy(cb.asc(compareBy.get(sortBy).apply(userJoin)));
          break;
        case "item":
          cq.orderBy(cb.asc(compareBy.get(sortBy).apply(itemJoin)));
          break;
        case "lastbid":
          cq.orderBy(cb.asc(compareBy.get(sortBy).apply(subRoot)));
          break;
        default:
          cq.orderBy(cb.asc(compareBy.get(sortBy).apply(root)));
          break;
      }
    } else {
      switch (sortBy) {
        case "seller":
          cq.orderBy(cb.desc(compareBy.get(sortBy).apply(userJoin)));
          break;
        case "item":
          cq.orderBy(cb.desc(compareBy.get(sortBy).apply(itemJoin)));
          break;
        case "lastbid":
          cq.orderBy(cb.desc(compareBy.get(sortBy).apply(subRoot)));
          break;
        default:
          cq.orderBy(cb.desc(compareBy.get(sortBy).apply(root)));
          break;
      }
    }

    List<Deal> result = new ArrayList<>();
    try {
      TypedQuery<Deal> query = session.createQuery(cq);
      query.setFirstResult((currentPage - 1) * pageSize);
      query.setMaxResults(pageSize);
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
    return result;
  }

  @Override
  public Long findAmountByStatus(String status) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);

    Root<Deal> root = cq.from(Deal.class);

    switch (status) {
      case "open":
        cq.where(cb.greaterThan(root.get(Deal_.closeTime),LocalDateTime.now()));
        break;
      case "closed":
        cq.where(cb.lessThan(root.get(Deal_.closeTime),LocalDateTime.now()));
        break;
      case "all":
      default:  // do nothing
    }
    cq.select(cb.count(root));

    Query<Long> query = session.createQuery(cq);
    Long result = query.getSingleResult();

    session.close();
    return result;
  }

  @Override
  public boolean checkIfAnyOpenDealsByItemId(int itemId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);

    Root<Deal> root = cq.from(Deal.class);
    cq.select(cb.count(root))
        .where(cb.equal(root.get(Deal_.item),itemId))
        .where(cb.isTrue(root.get(Deal_.status)));

    Query<Long> query = session.createQuery(cq);
    Long count = query.getSingleResult();

    session.close();
    return count > 0;
  }
}