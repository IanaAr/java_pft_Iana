package ru.stqa.pft.mantisbt.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
  @Test
  public void testRegistration() {
    app.registration().start("user1", "local@host.localdomain");
  }
}

