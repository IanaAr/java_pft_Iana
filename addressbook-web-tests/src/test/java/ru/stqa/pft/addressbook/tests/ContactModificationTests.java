package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
    app.getContactHelper().initContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData("TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST 1", "22222", "2222222222", "922222222", "222222", "qqq@qq.qqq", "www@www.ww", "ww@ww.ww", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactModificationTwoFields() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, null, null, null, null, null, null, null, "test1"), true);
    }
    app.getContactHelper().initContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData("TEST", null, "TEST", null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactModificationOneField() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    }
    app.getContactHelper().initContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData("TEST", null, null, null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactModificationZeroField() {
    app.getNavigationHelper().goToHomePage();
    if ( ! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    }
    app.getContactHelper().initContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }
}
