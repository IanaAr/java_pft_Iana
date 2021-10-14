package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.testng.annotations.DataProvider;

import javax.persistence.*;
import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "middlename")
  private String middlename;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Column(name = "company")
  private String company;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homephone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workphone;
  @Transient
  private String allphones;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String firstemail;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String secondemail;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String thirdemail;
  @Transient
  private String allemails;
  @Expose
  @Transient
  private String group;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;
  @Column(name = "deprecated")
  private Date deprecated;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }


  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withFirstEmail(String firstemail) {
    this.firstemail = firstemail;
    return this;
  }

  public ContactData withSecondEmail(String secondemail) {
    this.secondemail = secondemail;
    return this;
  }


  public ContactData withThirdEmail(String thirdemail) {
    this.thirdemail = thirdemail;
    return this;
  }


  public ContactData withAllEmails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
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

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homephone;
  }

  public String getMobilePhone() {
    return mobilephone;
  }

  public String getWorkPhone() {
    return workphone;
  }

  public String getAllphones() {
    return allphones;
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

  public String getAllemails() {
    return allemails;
  }

  public String getGroup() {
    return group;
  }

  public File getPhoto() {
    return  new File(photo);
  }

}