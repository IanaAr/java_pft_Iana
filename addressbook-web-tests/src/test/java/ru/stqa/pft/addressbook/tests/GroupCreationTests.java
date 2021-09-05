package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationAllFields() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().intGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitCreation();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupCreationTwoFields() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().intGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", null));
    app.getGroupHelper().submitCreation();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupCreationOneField() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().intGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    app.getGroupHelper().submitCreation();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupCreationNoField() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().intGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData(null, null, null));
    app.getGroupHelper().submitCreation();
    app.getGroupHelper().returnToGroupPage();
  }
}

