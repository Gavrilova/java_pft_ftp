package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by irinagavrilova on 6/11/2016.
 */
public class ChangingPasswordHelper extends HelperBase {

  public ChangingPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetPassword(String resetURL, String password) {
    wd.get(resetURL);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
  public void goToManageUserPage() {

    click(By.xpath("//div[2]/p/span[1]/a"));
  }
  public void select(int id) { //id gets from table `mantis_user_table` in DB;
  click(By.xpath(String.format("/html/body/table[3]/tbody/tr[%s]/td[1]/a",(2+id))));
}
 // public void select(String username) {
 //   click(By.xpath(String.format("//a[.='%s'])", username)));
 // }

  public void resetPasword() {
    click(By.cssSelector("input[value='Reset Password']"));;
  }
/*
  public void reset(String resetLink, String password) {
    wd.get(resetLink);
    type(By.name("password"), password);
    type(By.name("passwordChanged"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
  */

}
