package com.epam.marketplace.dao.impl;

import com.epam.entities.Deal_;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.DealDao;
import com.epam.marketplace.entities.Deal;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

}
