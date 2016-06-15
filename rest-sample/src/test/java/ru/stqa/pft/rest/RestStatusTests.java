package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by irinagavrilova on 6/14/16.
 */
public class RestStatusTests extends TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }


  @Test
  public void testCreateStateIsOpen() throws IOException {
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    //System.out.println(issueId);
    skipIfNotFixed(issueId);
    System.out.println("Hello, world!");
  }
}
