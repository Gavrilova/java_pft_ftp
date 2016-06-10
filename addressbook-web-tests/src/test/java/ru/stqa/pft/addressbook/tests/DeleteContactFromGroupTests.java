package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 5/28/16.
 */
public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {

      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        Groups beforeTest1 = app.db().groups();
        GroupData groupTest1 = new GroupData().withName("test1");
        app.group().create(groupTest1);
        assertThat(app.db().groups().size(), equalTo(beforeTest1.size() + 1));
        Groups afterTest1 = app.db().groups();
        assertThat(afterTest1, equalTo(
                beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

      }
      app.goTo().home();
      Contacts beforeContact1 = app.db().contacts();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Irina").withMiddlename("Aleksandrovna")
              .withLastname("Gavrilova").withAddress("Peregrine Falcon Dr.")
              .withHomePhone("123-456 7890").withEmail2("gavrilova.irina@gmail.com");
      app.contact().createContact(contact1);
      app.goTo().home();
      assertThat(app.db().contacts().size(), equalTo(beforeContact1.size() + 1));
      Contacts afterContact1 = app.db().contacts();
      assertThat(afterContact1, equalTo(
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((с) -> с.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void deleteContactFromGroupTest() {

    GroupData group = app.db().groups().iterator().next();

    boolean isGroupWithContacts = true;

    for (GroupData groupData : app.db().groups()) { //find group with at least one assosiate contact;
      if (groupData.getContacts().size() > 0) {
        group = groupData;
        isGroupWithContacts = false;
        break;
      }
    }

    if (isGroupWithContacts) { //add any contact to group;
      ContactData contact = app.db().contacts().iterator().next();
      app.goTo().home();
      app.contact().addContactToGroup(contact, group);
      app.goTo().home();
      assertThat(app.db().groups(group.getId()).getContacts(), equalTo(group.getContacts().withAdded(contact)));
    }

    Contacts beforeDeleting = app.db().groups(group.getId()).getContacts();
    ContactData assosiateContact = app.db().groups(group.getId()).getContacts().iterator().next();
    app.goTo().home();
    app.contact().deleteContactFromGroup(group, assosiateContact);
    app.goTo().home();
    Contacts afterDeleting = app.db().groups(group.getId()).getContacts();
    assertThat(afterDeleting, equalTo(beforeDeleting.without(assosiateContact)));
  }
}

