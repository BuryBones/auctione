package main.java.com.epam.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import main.java.com.epam.HibernateUtil;
import main.java.com.epam.dao.UserDao;
import main.java.com.epam.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
    super(User.class);
  }

  @Override
  public Optional<User> getUserByLogin(String login) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

    Root<User> root = criteriaQuery.from(User.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"),login));

    Query<User> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<User> users = query.getResultList();

    session.close();
    return users.isEmpty() ? Optional.empty() : Optional.ofNullable(users.get(0));
  }
}
