package com.epam;

import com.epam.entities.Bid;
import com.epam.entities.Deal;
import com.epam.entities.Item;
import com.epam.entities.Role;
import com.epam.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder registryBuilder =
            new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        registry = registryBuilder.build();

        MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Role.class);
        sources.addAnnotatedClass(Item.class);
        sources.addAnnotatedClass(Deal.class);
        sources.addAnnotatedClass(Bid.class);
        Metadata metadata = sources.getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
      } catch (Exception e) {
        e.printStackTrace();
        close();
      }
    }
    return sessionFactory;
  }
  public static void close() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}
