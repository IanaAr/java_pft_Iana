package ru.stqa.pft.mantisbt.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantisbt.model.MailMessage;
import ru.stqa.pft.mantisbt.model.UserData;
import ru.stqa.pft.mantisbt.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws MessagingException, IOException {
    String admin = "administrator";
    String password = "root";
    String newPassword = "password";
    app.sessionHelper().login(admin, password);

    app.navigationHelper().goToAdminPage();

    Users users = app.db().users();
    UserData user = users.getNotAdminUser();
    app.navigationHelper().sendChangingFormToUser(user);

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);

    String userEmail = user.getEmail();
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, userEmail);
    app.registration().finishRegistration(confirmationLink, newPassword);

    String userName = user.getUsername();
    assertTrue(app.newSession().login(userName, newPassword));
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}

