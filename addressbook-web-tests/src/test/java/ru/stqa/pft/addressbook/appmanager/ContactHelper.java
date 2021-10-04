package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import static org.testng.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void сontactCreationPage() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getMobilePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getFirstemail());
    type(By.name("email2"), contactData.getSecondemail());
    type(By.name("email3"), contactData.getThirdemail());
    attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void sendContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteContact() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    wd.findElement(By.cssSelector("div.msgbox"));
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String firstemail = wd.findElement(By.name("email")).getAttribute("value");
    String secondemail = wd.findElement(By.name("email2")).getAttribute("value");
    String thirdemail = wd.findElement(By.name("email3")).getAttribute("value");
    String adress = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstname(firstname)
            .withLastname(lastname)
            .withHomePhone(homephone)
            .withMobilePhone(mobilephone)
            .withWorkPhone(workphone)
            .withFirstEmail(firstemail)
            .withSecondEmail(secondemail)
            .withThirdEmail(thirdemail)
            .withAddress(adress);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void initContactModificationPage(int id) {
    wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"]")).click();
  }


  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact, boolean b) {
    сontactCreationPage();
    fillContactForm(contact, b);
    sendContactForm();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationPage(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectById(contact.getId());
    deleteContact();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cell = element.findElements(By.tagName("td"));
      String lastname = cell.get(1).getText();
      String firstname = cell.get(2).getText();
      String address = cell.get(3).getText();
      String allemails = cell.get(4).getText();
      // String[] phones = cell.get(5).getText().split("\n");
      String allphones = cell.get(5).getText();
      String attribute = element.findElement(By.tagName("input")).getAttribute("value");
      int id = Integer.parseInt(attribute);
      /*contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2])); */
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withAllPhones(allphones).withAllEmails(allemails).withAddress(address));
    }
    return new Contacts(contactCache);
  }
}

