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
 * Created by irinagavrilova on 5/27/16.
 */
public class AddContactToGroupTests extends TestBase {

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
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


  }

  @Test
  public void addContactToGroupTest() {
    Contacts contactsAtAll = app.db().contacts();
    ContactData contact = contactsAtAll.iterator().next();
    Groups groupsInContact = contact.getGroups();
    Groups groupsAtAll = app.db().groups();
    GroupData assosiateGroup;

    if (groupsInContact.size() == groupsAtAll.size()) {  //verify, that we have at least one group to add to contact:
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("AssosiateGroupName"));
      Groups groupsAddNew = app.db().groups();
      groupsAddNew.removeAll(groupsAtAll);
      assosiateGroup = groupsAddNew.iterator().next();

    } else {                                              //choose one NOT from contact.inGroups() list;
      groupsAtAll.removeAll(groupsInContact);
      assosiateGroup = groupsAtAll.iterator().next();
    }
    app.goTo().home();
    app.contact().addContactToGroup(contact, assosiateGroup);
    app.goTo().home();
    assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContact.withAdded(assosiateGroup)));
  }
}



