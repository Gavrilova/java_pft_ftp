package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

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
    app.change().goToManageUserPage();            //go to the page Manage Users
    String user = "user1";
    String password = "password";
    String password1 = "password";
    String email  = "user1@localhost.localdomain";  //need to get password for user in DB
    app.change().select(2);                         //choosing user by name or by id
    app.change().resetPasword();                    //Reset Password sends the confirmation URL via e-mail.

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetLink = findResetPasswordLink(mailMessages, email);
    System.out.println("resetLink = " + resetLink);
    app.change().resetPassword(resetLink, password1);

    HttpSession session = app.newSession();
    assertTrue(session.login(user, password1));
    assertTrue(session.isLogicInAs(user));
  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    System.out.println("mailMessage.text" + mailMessage.text);
    System.out.println("VerbalExpression.regex() = " + VerbalExpression.regex());
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    regex.getText(mailMessage.text);
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailserver(){
    app.mail().stop();
  }
}


