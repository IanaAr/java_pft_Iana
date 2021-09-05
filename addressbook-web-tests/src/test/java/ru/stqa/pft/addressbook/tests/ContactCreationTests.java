package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().goToContactCreationPage();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Test", "Test", "Test", "Title", "Company", "Address", "1111111", "9111111111", "922222222", "1111111", "qw@qw.qw", "qw@qw.qw", "qw@qw.qw", "test1"), true);
    app.getContactHelper().sendContactForm();
    app.getContactHelper().returnToContactCreationPage();
  }

}

