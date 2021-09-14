package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 10);
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Test", null, "Test", null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 10);
  }

  @Test
  public void testContactCreationOneField() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 10);
  }

  @Test
  public void testContactCreationZeroFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, "[none]"), true);
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 10);
  }
}

