package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.navigationHelper().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().
            withFirstname("TEST").withMiddlename("TEST").
            withLastname("TEST").withCompany("TEST").
            withAddress("TEST").withPhone("11111111111").
            withEmail("ww@ww.ww").withGroup("[none]");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.navigationHelper().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("TEST1").withLastname("TEST1").withGroup("[none]");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

