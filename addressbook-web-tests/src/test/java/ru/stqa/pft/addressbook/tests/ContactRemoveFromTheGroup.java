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

public class ContactRemoveFromTheGroup extends TestBase {


  @BeforeMethod
  public void insurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.navigationHelper().groupPage();
      app.group().create(new GroupData().withName("test 1").withHeader("test 1").withFooter("test 1"));
    }

    if (app.db().contacts().size() == 0) {
      GroupData groups = app.db().groups().iterator().next();
      app.navigationHelper().homePage();
      app.contact().create(new ContactData()
              .withFirstname("TEST").withMiddlename("TEST").withLastname("TEST")
              .withCompany("TEST").withAddress("TEST")
              .withHomePhone("homephone").withMobilePhone("mobilephone").withWorkPhone("workphone")
              .withFirstEmail("firstemail").withSecondEmail("secondemail").withThirdEmail("thirdemail").inGroup(groups), true);
    }

    Contacts contacts = app.db().contacts();
    if (contacts.getContactWithAGroup() == null) {
      GroupData group = app.db().groups().iterator().next();
      ContactData contact = app.db().contacts().iterator().next();
      app.navigationHelper().homePage();
      app.contact().addToAGroup(contact, group);
    }
  }

  @Test
  public void testContactRemoveFromAGroup() throws Exception {
    Contacts before = app.db().contacts();
    ContactData contactWithAGroup = before.getContactWithAGroup();
    GroupData group = contactWithAGroup.getGroups().iterator().next();

    Groups beforegroups = contactWithAGroup.getGroups();

    assertTrue(contactWithAGroup.getGroups().contains(group));

    app.navigationHelper().homePage();
    app.contact().removeContactFromAGroup(contactWithAGroup, group);

    Contacts after = app.db().contacts();
    ContactData updatedContact = after.findContactById(contactWithAGroup);
    Groups aftergroups = updatedContact.getGroups();

    assertFalse(updatedContact.getGroups().contains(group));
    assertEquals(after.size(), before.size());
    assertEquals(aftergroups.size(), beforegroups.size() - 1);
    verifyContactListInUi();
  }

}
