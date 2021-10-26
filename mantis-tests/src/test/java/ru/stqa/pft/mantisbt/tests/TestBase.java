package ru.stqa.pft.mantisbt.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantisbt.appmanager.ApplicationManager;

import java.math.BigInteger;


public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    // app.ftp().restore("config_inc.php.back", "config_inc.php");
    app.stop();
  }

  private boolean isIssueOpen(int issueId) {
    IssueData issueData;
    try {
      MantisConnectPortType ms = app.sp().getMantisConnect();
      issueData = ms.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    } catch (Exception e) {
      throw new RuntimeException("Problems with obtaining Issue", e);
    }
    ObjectRef resolution = issueData.getResolution();
    ObjectRef status = issueData.getStatus();
    return !resolution.getName().equals("fixed") || !status.getName().equals("closed");
  }


  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      System.out.println("Skipping test for Issue = " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
