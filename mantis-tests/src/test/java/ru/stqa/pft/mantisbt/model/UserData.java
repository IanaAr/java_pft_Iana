package ru.stqa.pft.mantisbt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  private int id = Integer.MAX_VALUE;

  @Column (name = "username")
  private String username;

  @Column (name = "email")
  private String email;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id && Objects.equals(username, userData.username) && Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email);
  }

  @Override
  public String toString() {
    return "UserData{" +
            "username='" + username + '\'' +
            "email='" + email + '\'' +
            '}';
  }

  public String getUsername() {
    return username;
  }


  public String getEmail() {
    return email;
  }


  public int getId() {
    return id;
  }

  public void withUsername(String username) {
    this.username = username;
  }

  public void withId(int id) {
    this.id = id;
  }

  public void withEmail(String email) {
    this.email = email;
  }

}
