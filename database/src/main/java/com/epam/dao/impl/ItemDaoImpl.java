package main.java.com.epam.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import main.java.com.epam.HibernateUtil;
import main.java.com.epam.dao.ItemDao;
import main.java.com.epam.entities.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

  public ItemDaoImpl() {
    super(Item.class);
  }

  public Item getItemByName(String name) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

    Root<Item> root = criteriaQuery.from(Item.class);
    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name));

    Query<Item> query = session.createQuery(criteriaQuery);
    query.setMaxResults(1);
    List<Item> result = query.getResultList();

    session.close();
    return result.get(0);
  }

}
