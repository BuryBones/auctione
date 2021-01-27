package com.epam.dao.impl;

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

public class BidDaoImpl extends GenericDaoImpl<Bid> implements BidDao {

  public BidDaoImpl() {
    super(Bid.class);
  }

  @Override
  public List<Bid> getBidsFromDate(Date dateFrom) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Bid> criteriaQuery = criteriaBuilder.createQuery(Bid.class);

    Root<Bid> root = criteriaQuery.from(Bid.class);
    criteriaQuery.select(root)
        .where(criteriaBuilder.greaterThanOrEqualTo(root.get("dateAndTime"), dateFrom));

    Query<Bid> query = session.createQuery(criteriaQuery);
    List<Bid> result = query.getResultList();

    session.close();
    return result;
  }

}
