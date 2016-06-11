package ru.stqa.pft.mantis.model;

/**
 * Created by irinagavrilova on 6/11/2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
