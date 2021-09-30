package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {
    app.navigationHelper().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Test").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    int index = 0;
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withId(before.get(index).getId()).
            withFirstname("TEST").withMiddlename("TEST").withLastname("TEST").
            withCompany("TEST").withAddress("TEST").
            withPhone("11111111111").withEmail("ww@ww.ww").withGroup("[none]");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
