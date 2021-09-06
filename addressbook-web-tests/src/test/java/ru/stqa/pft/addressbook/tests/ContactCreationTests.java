package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Test", null, "Test", null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
  }

  @Test
  public void testContactCreationOneField() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
  }

  @Test
  public void testContactCreationZeroFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
  }
}

