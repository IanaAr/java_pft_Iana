package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(0);
    app.getContactHelper().fillContactForm(new ContactData("TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST 1", "22222", "2222222222", "922222222", "222222", "qqq@qq.qqq", "www@www.ww", "ww@ww.ww", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  public void testContactModificationTwoFields() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(1);
    app.getContactHelper().fillContactForm(new ContactData("TEST", null, "TEST", null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  public void testContactModificationOneField() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    }
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(2);
    app.getContactHelper().fillContactForm(new ContactData("TEST", null, null, null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  public void testContactModificationZeroField() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    }
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(0);
    app.getContactHelper().fillContactForm(new ContactData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
