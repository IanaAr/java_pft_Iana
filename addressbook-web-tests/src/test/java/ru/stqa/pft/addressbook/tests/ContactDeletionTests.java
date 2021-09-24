package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", null, "Test", null, null, null, null, "[none]"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    int index = 0;
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
