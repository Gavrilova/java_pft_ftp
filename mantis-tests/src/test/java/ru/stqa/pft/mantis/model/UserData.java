package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by irinagavrilova on 6/12/2016.
 */


@Entity //Объявляет класс UserData привязанной к базе
@Table(name = "mantis_user_table")

public class UserData {


  @Id
  @Column(name = "id")
  private int id;


  @Column(name = "username")
  //@Type(type = "varchar")
  private String username;


  @Column(name = "email")
  // @Type(type = "varchar")
  private String email;
/*
@Column (name = "access_level")
@Type(type = "integer")
private int access_level
 */

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    if (email == null) {
      email = "";
    }
    return email;
  }

}