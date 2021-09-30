package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.navigationHelper().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("TEST").withMiddlename("TEST").
            withLastname("TEST").withCompany("TEST").
            withAddress("TEST").withPhone("11111111111").
            withEmail("ww@ww.ww").withGroup("[none]");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.navigationHelper().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("TEST1").withLastname("TEST1").withGroup("[none]");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}

