package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("TEST", "TEST", "TEST", "TEST", "TEST", "11111111111", "ww@ww.ww", "[none]");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Test", null, "Test", null, null, null, null, "[none]");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

