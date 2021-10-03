package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreationAllFields() throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("TEST").withMiddlename("TEST")
            .withLastname("TEST").withCompany("TEST").withAddress("TEST")
            .withHomePhone("2222").withMobilePhone("11111111111").withWorkPhone("11111")
            .withFirstEmail("ww@ww.ww").withSecondEmail("www@w.w").withThirdEmail("ww@www.w")
            .withAddress("testing street, testing home 4, testing appart. 11")
            .withGroup("[none]");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );
  }

  @Test
  public void testContactCreationTwoFields() throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("TEST1").withLastname("TEST1").withGroup("[none]");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );
  }

  @Test
  public void testBadContactCreationTwoFields() throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("TEST1'").withLastname("TEST1").withGroup("[none]");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }
}

