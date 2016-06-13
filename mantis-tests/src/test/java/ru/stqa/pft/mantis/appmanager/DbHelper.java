package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by irinagavrilova on 6/12/2016.
 */
public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData").list();
    for (UserData users : result) {
      System.out.println(users);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData users(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from UserData where id = " + id;
    System.out.println(request);
    List<UserData> resultUser = session.createQuery(request).list();
    UserData result = resultUser.iterator().next();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public Users notAdministrators() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from UserData where access_level != 90";
    List<UserData> result = session.createQuery(request).list();
    for (UserData users : result) {
      System.out.println(users);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}
