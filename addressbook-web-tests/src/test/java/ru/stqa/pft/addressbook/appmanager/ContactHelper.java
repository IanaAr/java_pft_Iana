package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import static org.testng.Assert.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToContactCreationPage() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobphone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("fax"), contactData.getFaxphone());
    type(By.name("email"), contactData.getFirstemail());
    type(By.name("email2"), contactData.getSecondemail());
    type(By.name("email2"), contactData.getThirdemail());
  }


  public void sendContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToContactCreationPage() {
    wd.get("http://localhost/addressbook/index.php");
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void initContactModificationPage() {
    click(By.xpath("//img[@alt='Edit']"));
  }


  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
}

