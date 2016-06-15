package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

/**
 * Created by irinagavrilova on 6/14/16.
 */
public class TestBase {

  public Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    boolean isOpen = true;
    Set<Issue> issues = getIssues();
    Issue choosingIssue = null;
    for (Issue issue : issues) {
      if (issue.getId() == issueId) {
        choosingIssue = issue;
      }
    }
    // Types of State:
    // 0 - Open
    // 1 - in Progress
    // 2 - Resolved
    // 3 - Closed
    // 4 - Reopened

    if ((choosingIssue.getState() == 2) || (choosingIssue.getState() == 3)) {
      isOpen = false;
    }
    return isOpen;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}