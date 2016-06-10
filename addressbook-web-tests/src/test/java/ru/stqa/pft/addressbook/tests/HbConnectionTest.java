package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by irinagavrilova on 5/23/16.
 */
public class HbConnectionTest {

  private SessionFactory sessionFactory;

  // @ManyToMany(fetch = FetchType.EAGER) про ленивую и жадную загрузку связей, указать в аннотации к полю groups

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testHbConnectionGroups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    for (GroupData groups : result) {
      System.out.println(groups);
      System.out.println(groups.getContacts());
    }
    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void testHbConnectionContacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = 0000-00-00").list();

    session.getTransaction().commit();
    session.close();
      for (ContactData contact : result) {
      System.out.println(contact);
      System.out.println("Set of groups for contact:");
      System.out.println(contact.getGroups());
    }
  }

}
