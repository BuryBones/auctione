package main.java.com.epam.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import main.java.com.epam.HibernateUtil;
import main.java.com.epam.dao.RoleDao;
import main.java.com.epam.entities.Role;
import main.java.com.epam.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

  public RoleDaoImpl() {
    super(Role.class);
  }

  @Override
  public Optional<Role> getRoleByName(String name) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

    Root<Role> root = criteriaQuery.from(Role.class);
    criteriaQuery.select(root)
        .where(criteriaBuilder.equal(root.get("roleName"),name));
    Query<Role> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<Role> roles = query.getResultList();

    session.close();
    return roles.isEmpty() ? Optional.empty() : Optional.ofNullable(roles.get(0));

  }
}
