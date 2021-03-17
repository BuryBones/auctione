package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.User_;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.UserDao;
import com.epam.marketplace.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userDao")
@Scope("prototype")
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

  @Override
  public Optional<User> findByEmail(String email) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

    Root<User> root = criteriaQuery.from(User.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.email),email));

    Query<User> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<User> users = query.getResultList();

    session.close();
    return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
  }

  @Override
  public List<User> findAllWithRoles() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

    Root<User> root = criteriaQuery.from(User.class);
    root.fetch(User_.userRoles, JoinType.LEFT);
    criteriaQuery.distinct(true);
    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(User_.id)));

    Query<User> query = session.createQuery(criteriaQuery);
    List<User> result = query.getResultList();

    session.close();
    return result;
  }
}
