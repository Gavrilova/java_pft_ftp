package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import static ru.stqa.pft.mantis.tests.TestBase.app;

/**
 * Created by irinagavrilova on 5/30/16.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {

    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
