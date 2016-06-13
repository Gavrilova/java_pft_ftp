package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by irinagavrilova on 6/11/2016.
 */
public class ChangingPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws InterruptedException, MessagingException, IOException {
    app.change().login("administrator", "root");  //enter to MantisBT as administrator using GUI
    app.change().goToManageUserPage();//go to the page Manage Users
    long currentTimeMillis = System.currentTimeMillis();
    String password1 = "password" + currentTimeMillis;
    System.out.println("password is changed to: " + password1);
    Users users = app.db().users();
    UserData randomUser = users.stream()
            .filter(( u ) -> ! u.getUsername().equals("administrator")).iterator().next();
    app.change().select(randomUser.getId());                         //choosing user by id
    app.change().resetPasword();                    //Reset Password sends the confirmation URL via e-mail.

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetLink = findResetPasswordLink(mailMessages, randomUser.getEmail());
    System.out.println("resetLink = " + resetLink);
    app.change().resetPassword(resetLink, password1);

    HttpSession session = app.newSession();
    assertTrue(session.login(randomUser.getUsername(), password1));
    assertTrue(session.isLogicInAs(randomUser.getUsername()));
  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    regex.getText(mailMessage.text);
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailserver(){
    app.mail().stop();
  }
}


