package ru.stqa.pft.bugify.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class IssueTests extends TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  @Test
  public void testSkipIssue() throws IOException {
    int issueId = 1559;
    skipIfNotFixed(issueId);
    System.out.println("Running test for Issue = " + issueId);
  }
}
