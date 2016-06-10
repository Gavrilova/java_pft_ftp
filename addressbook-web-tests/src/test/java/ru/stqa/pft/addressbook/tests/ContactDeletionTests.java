package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactDeletionTests extends TestBase {
  private boolean creating;

  @BeforeMethod
  public void ensurePreconditions() {
    creating = false;
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        creating = true;
        app.goTo().groupPage();
        Groups beforeTest1 = app.db().groups();
        GroupData groupTest1 = new GroupData().withName("test1");
        app.group().create(groupTest1);
        assertThat(app.group().count(), equalTo(beforeTest1.size() + 1));
        Groups afterTest1 = app.db().groups();
        assertThat(afterTest1, equalTo(
                beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

      }
      Contacts beforeContact1 = app.db().contacts();
      app.goTo().home();
      app.goTo().addNew();
      ContactData contact1 = new ContactData().withFirstname("Ira").withMiddlename("Aleksandrovna")
              .withLastname("Gavrilova").withNickname("myNickname").withTitle("test4")
              .withAddress("Peregrine Falcon Dr.").withHomePhone("123-456 7890").withMobilePhone("234-567 8901")
              .withWorkPhone("345-678 9012").withFax("5647").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/")
              //.withGroup("test1")
      ;
      app.contact().createContact(contact1);
      app.goTo().home();
      assertThat(app.db().contacts().size(), equalTo(beforeContact1.size() + 1));
      Contacts afterContact1 = app.db().contacts();
      Contacts before = beforeContact1.withAdded(
              contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()));
      assertThat(afterContact1, equalTo(before));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().home();
    Contacts beforeContact = app.db().contacts();
    ContactData deletedContact = beforeContact.iterator().next();
    app.contact().deleteContact(deletedContact);
    app.goTo().home();
    assertThat(app.db().contacts().size(), equalTo(beforeContact.size() - 1));
    Contacts afterContact = app.db().contacts();
    assertThat(afterContact, equalTo(beforeContact.without(deletedContact)));
    verifyContactListInUI();
  }


  @AfterMethod
  public void ensurePostconditions() {
    if (creating) {

      Groups beforeGroup = app.db().groups();
      app.goTo().groupPage();
      GroupData deletedGroup = beforeGroup.iterator().next();
      app.group().delete(deletedGroup);
      assertThat(app.db().groups().size(), equalTo(beforeGroup.size() - 1));
      Groups afterGroup = app.db().groups();
      assertThat(afterGroup, equalTo(beforeGroup.without(deletedGroup)));
    }
  }
}
