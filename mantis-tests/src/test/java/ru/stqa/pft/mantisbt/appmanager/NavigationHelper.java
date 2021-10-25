package ru.stqa.pft.mantisbt.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.mantisbt.model.UserData;


public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void goToAdminPage() {
    wd.findElement(By.cssSelector("a[href=\"/mantisbt/manage_overview_page.php\"]")).click();
    wd.findElement(By.cssSelector("a[href=\"/mantisbt/manage_user_page.php\"]")).click();
  }

  public void sendChangingFormToUser (UserData userData) {
    selectById(userData.getId());
    click(By.cssSelector("input[value ='Сбросить пароль']"));
  }

  private void selectById(int id) {
    wd.findElement(By.cssSelector("a[href=\"manage_user_edit_page.php?user_id=" + id + "\"]")).click();
  }
}
