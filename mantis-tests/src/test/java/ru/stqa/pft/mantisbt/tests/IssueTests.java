package ru.stqa.pft.mantisbt.tests;

import org.testng.annotations.Test;

public class IssueTests extends TestBase {

  @Test
  public void testSkipIssue() {
    int issueId = 1;
    skipIfNotFixed(issueId);
    System.out.println("Running test for Issue = " + issueId);
  }
}
