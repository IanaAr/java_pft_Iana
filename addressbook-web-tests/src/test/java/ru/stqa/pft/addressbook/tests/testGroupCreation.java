package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class testGroupCreation extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroupPage();
    app.intGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.submitCreation();
    app.returnToGroupPage();
  }
}

