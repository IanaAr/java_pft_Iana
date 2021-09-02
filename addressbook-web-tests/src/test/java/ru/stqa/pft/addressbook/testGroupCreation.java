package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class testGroupCreation extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    intGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitCreation();
    returnToGroupPage();
  }
}

