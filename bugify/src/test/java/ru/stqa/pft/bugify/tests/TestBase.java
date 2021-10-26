package ru.stqa.pft.bugify.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import ru.stqa.pft.bugify.model.Issue;


import java.io.IOException;
import java.util.Iterator;
import java.util.Set;


public class TestBase {


  private boolean isIssueOpen(int issueId) {
    Issue issue = getIssue(issueId);
    if (issue != null) {
      String state = issue.getState();
      return !state.equals("3");
    }
    throw new RuntimeException(String.format("Issue %d not found", issueId));
  }

  private Issue getIssue(int issueId) {
    String path = String.format("https://bugify.stqa.ru/api/issues/%d.json", issueId);
    String json = RestAssured.get(path).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issueSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Iterator<Issue> iterator = issueSet.iterator();
    if (iterator.hasNext())
      return iterator.next();
    return null;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      System.out.println("Skipping test for Issue = " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


