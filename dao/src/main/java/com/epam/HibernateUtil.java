package main.java.com.epam;

import java.util.HashMap;
import java.util.Map;
import main.java.com.epam.entities.Bid;
import main.java.com.epam.entities.Deal;
import main.java.com.epam.entities.Item;
import main.java.com.epam.entities.Role;
import main.java.com.epam.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder registryBuilder =
            new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

//        Map<String,String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, "org.postgresql.Driver");
//        settings.put(Environment.URL,"jdbc:postgresql://localhost:5432/market");
//        settings.put(Environment.USER,"postgres");
//        settings.put(Environment.PASS,"admin");
//        settings.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQL9Dialect");
//
//        registryBuilder.applySettings(settings);

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
