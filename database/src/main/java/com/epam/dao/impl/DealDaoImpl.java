package main.java.com.epam.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import main.java.com.epam.HibernateUtil;
import main.java.com.epam.dao.DealDao;
import main.java.com.epam.entities.Deal;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DealDaoImpl extends GenericDaoImpl<Deal> implements DealDao {

  public DealDaoImpl() {
    super(Deal.class);
  }

  @Override
  public List<Deal> getDealsByStatus(boolean status) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Deal> criteriaQuery = criteriaBuilder.createQuery(Deal.class);

    Root<Deal> root = criteriaQuery.from(Deal.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("status"),status));

    Query<Deal> query = session.createQuery(criteriaQuery);
    List<Deal> result = query.getResultList();

    session.close();
    return result;
  }

  @Override
  public List<Deal> getDealsFromDate(Date dateFrom) {
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
