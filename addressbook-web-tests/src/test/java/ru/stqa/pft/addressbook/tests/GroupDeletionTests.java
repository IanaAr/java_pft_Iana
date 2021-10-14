package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.navigationHelper().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deleteGroup = before.iterator().next();
    app.navigationHelper().groupPage();
    app.group().delete(deleteGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deleteGroup)));
  }


}
