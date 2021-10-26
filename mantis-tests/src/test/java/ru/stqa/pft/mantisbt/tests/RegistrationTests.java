package ru.stqa.pft.mantisbt.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantisbt.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws MessagingException, IOException {
    String email = "local@host.localdomain";
    String user = "user";
    String password = "pas";
    app.registration().startRegistration(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finishRegistration(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}

