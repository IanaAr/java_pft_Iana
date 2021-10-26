package ru.stqa.pft.mantisbt.tests;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantisbt.model.Issue;
import ru.stqa.pft.mantisbt.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

  @Test
  public void  testGetProjects () throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.sp().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue () throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.sp().getProjects();
    Issue issue = new Issue().withSummary("Test issue").
            withDescription("Test issue description")
            .withSummary("Test issue summary")
            .withProject(projects.iterator().next());
    Issue created = app.sp().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());

  }
}
