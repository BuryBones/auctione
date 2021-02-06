package com.epam.marketplace.dao.impl;

import com.epam.entities.User_;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {

  @Override
  public Optional<User> findByLogin(String login) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

    Root<User> root = criteriaQuery.from(User.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.login),login));

    Query<User> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<User> users = query.getResultList();

    session.close();
    return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
  }

}
