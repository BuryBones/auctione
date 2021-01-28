package com.epam.dao;

import com.epam.HibernateUtil;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SetAttribute;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public interface CommonDao<T> {

  Class<T> getType();

  default Optional<T> findById(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    T result = session.get(getType(),id);
    session.close();
    return Optional.ofNullable(result);
  }

  default Optional<T> findByIdWithFields(int id, String... fields) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getType());

    Root<T> root = criteriaQuery.from(getType());
    if (fields != null && fields.length > 0) {
      for (String field: fields) {
        root.fetch(field, JoinType.LEFT);
      }
    }

    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

    Query<T> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<T> result = query.getResultList();

    session.close();
    return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
  }

  default Optional<T> findByIdWithAttributes(int id, SetAttribute<T,?> fields) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getType());

    Root<T> root = criteriaQuery.from(getType());
    root.fetch(fields,JoinType.LEFT);
//    if (fields.length > 0) {
//      for (SetAttribute<User,?> field: fields) {
//        root.fetch(field, JoinType.LEFT);
//      }
//    }

    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

    Query<T> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<T> result = query.getResultList();

    session.close();
    return result.isEmpty() ? Optional.empty() : Optional.ofNullable(result.get(0));
  }

  default List<T> findAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(getType());
    criteria.from(getType());
    List<T> result = session.createQuery(criteria).getResultList();
    session.close();
    return result;
  }

  default void save(T object) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.save(object);
      transaction.commit();
    } catch (RuntimeException e) {
      try {
        if (transaction != null) {
          transaction.rollback();
        }
      } catch (HibernateException he) {
        System.err.println("Transaction rollback not successful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }

  default void update(T object) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.update(object);
      transaction.commit();
    } catch (RuntimeException e) {
      try {
        if (transaction != null) {
          transaction.rollback();
        }
      } catch (HibernateException he) {
        System.err.println("Transaction rollback not successful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }

  default void delete(T object) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    try {
      transaction = session.beginTransaction();
      session.delete(object);
      transaction.commit();
    } catch (RuntimeException e) {
      try {
        if (transaction != null) {
          transaction.rollback();
        }
      } catch (HibernateException he) {
        System.err.println("Transaction rollback not successful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }

  default void refresh(T object) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.refresh(object);
    session.close();
  }

}
