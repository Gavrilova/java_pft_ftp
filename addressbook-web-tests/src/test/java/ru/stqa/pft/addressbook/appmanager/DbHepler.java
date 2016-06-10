package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by irinagavrilova on 5/25/16.
 */
public class DbHepler {

  private final SessionFactory sessionFactory;

  public DbHepler() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    for (GroupData groups : result) {
      System.out.println(groups);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public GroupData groups(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from GroupData where group_id = " + id;
    System.out.println(request);
    List<GroupData> resultGroup = session.createQuery(request).list();
    GroupData result =  resultGroup.iterator().next();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> resultContact = session.createQuery("from ContactData where deprecated = 0000-00-00").list();
    for (ContactData contacts : resultContact) {
      System.out.println(contacts);
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(resultContact);
  }

  public ContactData contacts(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from ContactData where id = " + id;
    List<ContactData> resultContact = session.createQuery(request).list();
    ContactData result =  resultContact.iterator().next();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  }

