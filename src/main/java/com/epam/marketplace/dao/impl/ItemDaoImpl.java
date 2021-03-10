package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.Item_;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.ItemDao;
import com.epam.marketplace.entities.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component("itemDao")
public class ItemDaoImpl implements ItemDao {

  public Optional<Item> findByName(String name) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

    Root<Item> root = criteriaQuery.from(Item.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Item_.name), name));

    Query<Item> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<Item> result = query.getResultList();

    session.close();
    return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
  }

  @Override
  public List<Item> findByUserId(int userId) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

    Root<Item> root = criteriaQuery.from(Item.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Item_.user),userId));

    Query<Item> query = session.createQuery(criteriaQuery);
    List<Item> result = query.getResultList();

    session.close();
    return result;
  }
}
