package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  protected String adminLogin = app.getProperty("web.adminLogin");
  protected String adminPass = app.getProperty("web.adminPassword");
  protected String connectURL = app.getProperty("web.connectURL");

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    boolean isOpen = true;
    MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("web.connectURL")));
    IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
    String status = issueData.getStatus().getName();
    System.out.println("Status of the issue " + issueId + " is " + status);
    if (status.equals("closed") || (status.equals("resolved"))){
      isOpen = false;
    }
    return isOpen;
  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException { //invoke before each test, if the bug doesn't fix yet.
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

}