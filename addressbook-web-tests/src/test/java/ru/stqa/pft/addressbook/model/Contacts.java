package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegate = new HashSet<ContactData>(contacts);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public ContactData findContactById(ContactData contactData) {
    for (ContactData contact : this) {
      if (contact.getId() == contactData.getId()) {
        return contact;
      }
    }
    return null;
  }

  public ContactData getContactWithAGroup() {
    for (ContactData contactData : delegate) {
      Groups contactGroups = contactData.getGroups();
      if (contactGroups.size() > 0) {
        return contactData;
      }
    }
    return null;
  }

  public ContactData getContactWithoutAllGroups(Groups groups) {
    for (ContactData contactData : delegate) {
      Groups contactGroups = contactData.getGroups();
      if (contactGroups.size() != groups.size()) {
        return contactData;
      }
    }
    return null;
  }
}
