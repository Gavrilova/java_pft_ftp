package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by irinagavrilova on 4/19/16.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      Groups beforeTest1 = app.db().groups();
      GroupData groupTest1 = new GroupData().withName("test1");
      app.group().create(groupTest1);
      assertThat(app.db().groups().size(), equalTo(beforeTest1.size() + 1));
      Groups afterTest1 = app.db().groups();
      assertThat(afterTest1, equalTo(
              beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      verifyGroupListInUI();

    }
  }

  @Test
  public void testGroupModification() {

    Groups beforeGroup = app.db().groups();
    GroupData modifiedGroup = beforeGroup.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("nameEdited").withHeader("headerEdited").withFooter("footerEdited");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.db().groups().size(), equalTo(beforeGroup.size()));
    Groups afterGroup = app.db().groups();
    assertThat(afterGroup, equalTo(beforeGroup.without(modifiedGroup).withAdded(group)));

  }
}
