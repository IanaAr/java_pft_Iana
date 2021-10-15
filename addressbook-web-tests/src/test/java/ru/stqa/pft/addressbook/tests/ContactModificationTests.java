package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {
    if (app.db().contacts().size() == 0) {
      app.navigationHelper().homePage();
      app.contact().create(new ContactData()
              .withFirstname("TEST").withMiddlename("TEST").withLastname("TEST")
              .withCompany("TEST").withAddress("TEST")
              .withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
              .withFirstEmail("firstemail").withSecondEmail("secondemail").withThirdEmail("thirdemail")
              .withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("TEST").withMiddlename("TEST").withLastname("TEST")
            .withCompany("TEST").withAddress("TEST")
            .withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
            .withFirstEmail("firstemail").withSecondEmail("secondemail").withThirdEmail("thirdemail")
            .withGroup("[none]");
    app.navigationHelper().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUi();
  }
}
