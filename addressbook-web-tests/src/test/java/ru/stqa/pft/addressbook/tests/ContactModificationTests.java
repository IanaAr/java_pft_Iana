package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, "TEST", null, null, null, null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(0);
    ContactData contact = new ContactData(before.get(0).getId(), "TEST", "TEST", "TEST", "TEST", "TEST", "11111111111", "ww@ww.ww", "[none]");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(0);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModificationTwoFields() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, null, null, null, null, null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModificationPage(1);
    ContactData contact = new ContactData(before.get(1).getId(), "TEST", null, "TEST", null, null, null, null, null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(1);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
