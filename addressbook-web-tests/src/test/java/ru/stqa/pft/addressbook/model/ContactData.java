package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String homephone;
  private final String mobphone;
  private final String workphone;
  private final String faxphone;
  private final String firstemail;
  private final String secondemail;
  private final String thirdemail;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobphone, String workphone, String faxphone, String firstemail, String secondemail, String thirdemail) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobphone = mobphone;
    this.workphone = workphone;
    this.faxphone = faxphone;
    this.firstemail = firstemail;
    this.secondemail = secondemail;
    this.thirdemail = thirdemail;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobphone() {
    return mobphone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getFaxphone() {
    return faxphone;
  }

  public String getFirstemail() {
    return firstemail;
  }

  public String getSecondemail() {
    return secondemail;
  }

  public String getThirdemail() {
    return thirdemail;
  }
}
