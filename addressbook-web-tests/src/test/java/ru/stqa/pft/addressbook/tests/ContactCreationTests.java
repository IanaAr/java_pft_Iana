package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData()
            .withFirstname("TEST").withMiddlename("TEST")
            .withLastname("TEST").withCompany("TEST").withAddress("TEST")
            .withHomePhone("1111").withMobilePhone("11111111111").withWorkPhone("11111")
            .withFirstEmail("ww1@ww.ww").withSecondEmail("www1@w.w").withThirdEmail("ww1@www.w")
            .withAddress("testing street, testing home 1, testing appart.11")
            .withPhoto(new File("src/test/resources/nature.png"))
            .withGroup("[none]")});
    list.add(new Object[]{new ContactData()
            .withFirstname("TESTPhoto")
            .withLastname("TESTPhoto")
            .withPhoto(new File("src/test/resources/nature.png"))
            .withGroup("[none]")});
    list.add(new Object[]{new ContactData()
            .withFirstname("TEST2Fields")
            .withLastname("TEST2Fields")
            .withGroup("[none]")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreationAllFields(ContactData contact) throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))
    );
  }

  /* @Test
   public void testCurrentDir () {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/nature.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
   } */

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

