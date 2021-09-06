package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationAllFields() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }

  @Test
  public void testGroupCreationTwoFields() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", null));
  }

  @Test
  public void testGroupCreationOneField() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }

  @Test
  public void testGroupCreationNoField() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData(null, null, null));
  }
}

