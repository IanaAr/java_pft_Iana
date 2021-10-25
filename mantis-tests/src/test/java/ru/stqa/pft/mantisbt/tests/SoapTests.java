package ru.stqa.pft.mantisbt.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapTests {

  @Test
  public void  testGetProjects () throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType ms = new MantisConnectLocator()
            .getMantisConnectPort(new URL("https://localhost/mantisbt/api/soap/mantisconnect.php"));
    ProjectData[] projects = ms.mc_projects_get_user_accessible("administrator", "root");
    System.out.println(projects.length);
    for (ProjectData project : projects) {
      System.out.println(project.getName());
    }
  }
}
