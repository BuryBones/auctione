package main.java.com.epam.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import main.java.com.epam.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDaoImpl<T> {

  private final Class<T> type;

  /**
   * Constructor initializes a type variable used by other methods.
   * @param clazz is a class of T generic type.
   */
  public GenericDaoImpl(Class<T> clazz)
  {
    type = clazz;
  }

  public T findById(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    T result = session.get(type,id);
    session.close();
    return result;
  }

  public List<T> findAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(type);
    criteria.from(type);
    List<T> result = session.createQuery(criteria).getResultList();
    session.close();
    return result;
  }

  public void save(T object) {
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
        System.err.println("Transaction roleback not succesful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }

  public void update(T object) {
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
        System.err.println("Transaction roleback not succesful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }

  public void delete(T object) {
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
        System.err.println("Transaction roleback not succesful");
        System.out.println(he.getMessage());
      }
    } finally {
      session.close();
    }
  }
}
