package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.exec.ExecuteWatchdog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd; //чтобы к wd обратились только через метод getDriver надо сделать эту переменную private

  private String browser;
  private RegistrationHelper registrationHepler;
  private FtpHelper ftp;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target)))); //в методе init загружается только конфигурационный файл
  }

  public void stop() { // если драйвер останавливается в самом конце, но не был проинициализирован, поэтому stop() останов делаем с проверкой
    if (wd != null) {
    wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }
 public String getProperty(String key) {
   return properties.getProperty(key);
 }


  public RegistrationHelper registration() {
    //return new RegistrationHelper(this); // в качестве парамета передаем this, т.е. ссылка на Application Manager.
    //ApplicationManager нанимает помощника и передаем ему ссылку на самого себя
    if (registrationHepler == null) { // реализуем ленивую инициализацию: создаем поле registrationHepler и инициализируем его не в методе init(), а при первом обращении к методу registration()
      registrationHepler = new RegistrationHelper(this);
    }
    return registrationHepler;
  }

  public FtpHelper ftp() {
  if (ftp == null) {
    ftp = new FtpHelper(this);
  }
    return ftp;
  }

  public WebDriver getDriver() { //для того, чтобы инициализация стала ленивой, нужно ее перенести именно в этот метод
    if (wd == null) {  //драйвер будет инициализироватья в тот момент, когда к нему кто-то обратится и вызовет метод getDriver
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
