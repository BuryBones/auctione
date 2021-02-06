package com.epam.dao.impl;

import com.epam.entities.Bid_;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.HibernateUtil;
import com.epam.dao.BidDao;
import com.epam.entities.Bid;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

}
