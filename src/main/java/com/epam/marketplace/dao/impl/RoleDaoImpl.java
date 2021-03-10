package com.epam.marketplace.dao.impl;

import com.epam.marketplace.entities.Role_;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epam.marketplace.HibernateUtil;
import com.epam.marketplace.dao.RoleDao;
import com.epam.marketplace.entities.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoleDaoImpl implements RoleDao {

  @Override
  public Optional<Role> findByName(String name) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

    Root<Role> root = criteriaQuery.from(Role.class);
    criteriaQuery.select(root)
        .where(criteriaBuilder.equal(root.get(Role_.roleName),name));
    Query<Role> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<Role> roles = query.getResultList();

    session.close();
    return roles.isEmpty() ? Optional.empty() : Optional.ofNullable(roles.get(0));
  }
}
