package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePrecondition() {
    app.navigationHelper().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Test", null, "Test", null, null, null, null, "[none]"), true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    int index = 0;
    List<ContactData> before = app.contact().list();
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
