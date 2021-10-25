package ru.stqa.pft.mantisbt.appmanager;

import org.openqa.selenium.By;


public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void adminPage() {
    wd.findElement(By.cssSelector("a[href=\"/mantisbt/manage_overview_page.php\"]")).click();
    wd.findElement(By.cssSelector("a[href=\"/mantisbt/manage_user_page.php\"]")).click();
  }

}
