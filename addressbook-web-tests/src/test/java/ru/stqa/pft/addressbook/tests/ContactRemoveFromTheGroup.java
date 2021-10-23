package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactRemoveFromTheGroup extends TestBase {


  @BeforeMethod
  public void insurePrecondition() {
    if (app.db().contacts().size() == 0 || findContactWithoutAllGroups() == null) {
      app.navigationHelper().homePage();
      app.contact().create(new ContactData()
              .withFirstname("TEST").withMiddlename("TEST").withLastname("TEST")
              .withCompany("TEST").withAddress("TEST")
              .withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
              .withFirstEmail("firstemail").withSecondEmail("secondemail").withThirdEmail("thirdemail"), true);
    }
    if (app.db().groups().size() == 0) {
      app.navigationHelper().groupPage();
      app.group().create(new GroupData().withName("test 1").withHeader("test 1").withFooter("test 1"));
    }
  }

  @Test
  public void testContactRemoveFromAGroup() throws Exception {
    app.navigationHelper().homePage();
    Contacts before = app.db().contacts();
    ContactData removeFromAGroup = before.iterator().next();
    app.contact().removeContactFromAGroup(removeFromAGroup);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(removeFromAGroup).withAdded(removeFromAGroup)));
    verifyContactListInUi();
  }


  private ContactData findContactWithoutAllGroups() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    for (ContactData contactData : contacts) {
      Groups contactGroups = contactData.getGroups();
      if (contactGroups.size() != groups.size()) {
        return contactData;
      }
    }
    return null;
  }

}
