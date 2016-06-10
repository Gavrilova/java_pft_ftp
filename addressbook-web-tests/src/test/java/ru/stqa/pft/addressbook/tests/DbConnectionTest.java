package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by irinagavrilova on 5/23/16.
 */
public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?&serverTimezone=UTC" +
              "&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName((rs.getString("group_name")))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }

      ResultSet rsContacts = st.executeQuery("select id, firstname, middlename, lastname, nickname," +
              "company, title, address, home, mobile, work, fax, email, email2, email3, homepage from addressbook");
      Contacts contacts = new Contacts();
      while (rsContacts.next()) {
        contacts.add(new ContactData().withId(rsContacts.getInt("id"))
                .withFirstname(rsContacts.getString("firstname"))
                .withMiddlename(rsContacts.getString("middlename"))
                .withLastname(rsContacts.getString("lastname"))
                .withNickname(rsContacts.getString("nickname"))
                .withCompany(rsContacts.getString("company"))
                .withTitle(rsContacts.getString("title"))
                .withHomePhone(rsContacts.getString("home"))
                .withMobilePhone(rsContacts.getString("mobile"))
                .withWorkPhone(rsContacts.getString("work"))
                .withFax(rsContacts.getString("fax"))
                .withEmail(rsContacts.getString("email"))
                .withEmail2(rsContacts.getString("email2"))
                .withEmail3(rsContacts.getString("email3"))
                .withHomepage(rsContacts.getString("homepage")));
      }
      rsContacts.close();
      rs.close();
      st.close();
      conn.close();

      System.out.println(groups);
      System.out.println(contacts);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
