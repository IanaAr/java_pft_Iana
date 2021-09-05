package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().returnToContactCreationPage();
    app.getContactHelper().initContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData("TEST", "TEST", "TEST", "TEST", "TEST", "TEST", "TEST 1", "22222", "2222222222", "922222222", "222222", "qqq@qq.qqq", "www@www.ww", "ww@ww.ww", null), false);
    app.getContactHelper().submitContactModification();
  }


}
