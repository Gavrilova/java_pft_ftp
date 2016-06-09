package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by irinagavrilova on 5/31/16.
 */
public class RegistrationHelper { //этому помощнику браузер нужен

  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
   // wd = app.wd; //просим ссылку на driver у ApplicationManager //меняем на ленивую инициализацию
    wd = app.getDriver(); //метод будет инициализировать driver в момент первого обращения
  }
  public void start (String username, String email) {
  wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
