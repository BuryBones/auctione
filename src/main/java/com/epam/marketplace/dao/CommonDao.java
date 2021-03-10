package com.epam.marketplace.dao;

import com.epam.marketplace.HibernateUtil;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;
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

  default Optional<T> findByIdWithAttributes(int id, Attribute<T,?>... fields)
      throws IllegalArgumentException{
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getType());

    Root<T> root = criteriaQuery.from(getType());
    if (fields != null && fields.length > 0) {
      for (Attribute<T, ?> field: fields) {
        if (field instanceof SingularAttribute) {
          SingularAttribute<T, ?> attribute = (SingularAttribute<T, ?>) field;
          root.fetch(attribute, JoinType.LEFT);
        } else if (field instanceof PluralAttribute) {
          PluralAttribute<T, ?, ?> attribute = (PluralAttribute<T, ?, ?>) field;
          root.fetch(attribute, JoinType.LEFT);
        } else {
          throw new IllegalArgumentException(field + " is neither Singular nor Plural Attribute.");
        }
      }
    }

    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

    Query<T> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<T> result = query.getResultList();

    session.close();
    return Optional.ofNullable(result.get(0));
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
      e.printStackTrace();
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
