package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.allowTypes(new Class[]{ContactData.class});
    xstream.processAnnotations(ContactData.class);
    List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

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

