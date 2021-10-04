package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader =  new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split("; ");
      list.add(new Object[] {new ContactData()
              .withFirstname(split[0]).withMiddlename(split[1])
              .withLastname(split[2]).withCompany(split[3]).withAddress(split[4])
              .withHomePhone(split[5]).withMobilePhone(split[6]).withWorkPhone(split[7])
              .withFirstEmail(split[8]).withSecondEmail(split[9]).withThirdEmail(split[10])
              .withGroup(split[11])});
      line = reader.readLine();
    }
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

  @Test
  public void testContactCreationWithPhoto() throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/nature.png");
    ContactData contact = new ContactData()
            .withFirstname("TESTPhoto").withLastname("TEST1")
            .withGroup("[none]").withPhoto(photo);
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

