package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by irinagavrilova on 5/31/16.
 */
public class RegistrationHelper extends HelperBase { //этому помощнику браузер нужен

  public RegistrationHelper(ApplicationManager app) {
    super(app);
   // wd = app.wd; //просим ссылку на driver у ApplicationManager //меняем на ленивую инициализацию
    // wd = app.getDriver();метод будет инициализировать driver в момент первого обращения
  }
  public void start (String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));

  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

}
