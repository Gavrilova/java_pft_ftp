package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class ContactModificationTests extends TestBase {

  public File photo = new File("src/test/resources/Zello.png");

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
      ContactData contact1 = new ContactData().withFirstname("Irina")
              .withMiddlename("Aleksandrovna").withLastname("Gavrilova")
              .withNickname("myNickname").withTitle("test4").withAddress("Peregrine Falcon Dr.")
              .withHomePhone("123-456 7890").withWorkPhone("234-567 8901").withMobilePhone("345-678 9012")
              .withFax("1945").withEmail2("gavrilova.irina@gmail.com")
              .withHomepage("http://www.zello.com/")
              //.withGroup("test1")
      ;
      app.contact().createContact(contact1);
      app.goTo().home();
      assertThat(app.db().contacts().size(), equalTo(beforeContact1.size() + 1));
      Contacts afterContact1 = app.db().contacts();
      assertThat(afterContact1, equalTo(
              beforeContact1.withAdded(contact1.withId(afterContact1.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().home();
    Contacts beforeContact = app.db().contacts();
    ContactData modifiedContact = beforeContact.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("Ira")
            .withMiddlename("Aleksandrovna").withLastname("Gavrilova")
            .withNickname("editedNickname").withTitle("editedTEST")
            .withAddress("Peregrine Falcon Dr.").withCompany("Zello").withHomePhone("123-456 1234")
            .withMobilePhone("234-567 3457").withWorkPhone("345-678 0000").withFax("5647")
            .withEmail("email").withEmail2("gavrilova.irina@gmail.com").withEmail3("email3")
            .withHomepage("zello").withPhoto(photo);
    app.goTo().home();
    app.contact().modifyContact(contact);
    app.goTo().home();
    assertThat(app.db().contacts().size(), equalTo(beforeContact.size()));
    Contacts afterContact = app.db().contacts();
    Contacts toCompateContact = beforeContact.without(modifiedContact).withAdded(contact);
    assertThat(afterContact, equalTo(toCompateContact));
    verifyContactListInUI();
  }
}
