package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactHomePageEqualsTests extends TestBase {


  @BeforeMethod
  public void insurePrecondition() {
    if (app.db().contacts().size() == 0) {
      app.navigationHelper().homePage();
      app.contact().create(new ContactData().withFirstname("Test").withLastname("Test")
              .withAddress("Test")
              .withHomePhone("1111").withMobilePhone("111111111").withWorkPhone("11111")
              .withFirstEmail("Test@test").withSecondEmail("Test@test").withThirdEmail("Test@test")
              .withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactPhones() {
    app.navigationHelper().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  @Test
  public void testContactEmails() {
    app.navigationHelper().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllemails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }


  @Test
  public void testContactAddress() {
    app.navigationHelper().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(" "))
            .map(ContactHomePageEqualsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getFirstemail(), contact.getSecondemail(), contact.getThirdemail())
            .stream().filter((s) -> !s.equals(" "))
            .collect(Collectors.joining("\n"));

  }
}
