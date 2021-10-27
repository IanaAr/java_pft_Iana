package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ContactAddToAGroupTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {

    if (app.db().contacts().size() == 0) {
      app.navigationHelper().homePage();
      app.contact().create(new ContactData()
              .withFirstname("TEST").withMiddlename("TEST").withLastname("TEST")
              .withCompany("TEST").withAddress("TEST")
              .withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
              .withFirstEmail("firstemail").withSecondEmail("secondemail").withThirdEmail("thirdemail"), true);
    }
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (app.db().groups().size() == 0 || contacts.getContactWithoutAllGroups(groups) == null ) {
      app.navigationHelper().groupPage();
      app.group().create(new GroupData().withName("new group").withHeader("new group").withFooter("new group"));
    }
  }


  @Test
  public void testContactAddToAGroup() throws Exception {

    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contactData = before.getContactWithoutAllGroups(groups);
    GroupData groupData = contactData.findNewGroup(groups);

    assertFalse(contactData.getGroups().contains(groupData));

    app.navigationHelper().homePage();
    app.contact().addToAGroup(contactData, groupData);

    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    ContactData updatedContact = after.findContactById(contactData);
    assertTrue(updatedContact.getGroups().contains(groupData));
    verifyContactListInUi();

  }

}



