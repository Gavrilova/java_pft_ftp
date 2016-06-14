package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by irinagavrilova on 6/14/2016.
 */
public class SoapStatusTests extends TestBase{
  @Test
  public void testStatusNew() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000001);
    System.out.println("Hello, world!");
  }

  @Test
  public void testStatusClosed() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000002);
    System.out.println("Hello, world!");
  }
  @Test
  public void testStatusResolved() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000003);
    System.out.println("Hello, world!");
  }
  @Test
  public void testStatusConfirmed() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000004);
    System.out.println("Hello, world!");
  }
  @Test
  public void testStatusFeedback() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000005);
    System.out.println("Hello, world!");
  }
  @Test
  public void testStatusAcknowledged() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000006);
    System.out.println("Hello, world!");
  }
  @Test
  public void testStatusAssigned() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000007);
    System.out.println("Hello, world!");
  }
}
