package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      Groups beforeTest1 = app.db().groups();
      GroupData groupTest1 = new GroupData().withName("test1");
      app.group().create(groupTest1);
      assertThat(app.db().groups().size(), equalTo(beforeTest1.size() + 1));
      Groups afterTest1 = app.db().groups();
      assertThat(afterTest1, equalTo(beforeTest1.withAdded(groupTest1.withId(afterTest1.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups beforeGroup = app.db().groups();
    GroupData deletedGroup = beforeGroup.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.db().groups().size(), equalTo(beforeGroup.size() - 1));
    Groups afterGroup = app.db().groups();
    assertThat(afterGroup, equalTo(beforeGroup.without(deletedGroup)));
    verifyGroupListInUI();
  }
}
