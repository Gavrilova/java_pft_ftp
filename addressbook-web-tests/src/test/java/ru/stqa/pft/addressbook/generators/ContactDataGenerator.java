package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by irinagavrilova on 5/15/16.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }


  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    if (file.endsWith(".csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (file.endsWith(".xml")) {
      saveAsXml(contacts, new File(file));
    } else if (file.endsWith(".json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + file);
    }
  }


  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsoluteFile());
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstname(), contact.getMiddlename(), contact.getLastname(), contact.getNickname(),
                contact.getTitle(), contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
                contact.getWorkPhone(), contact.getFax(), contact.getCompany(), contact.getEmail2(),
                contact.getEmail3(), contact.getHomepage()));
              //  contact.getGroup()))

      }
    }
  }


  private List<ContactData> generateContact(int i) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("name %s", i))
              .withMiddlename(String.format("middlename %s", i))
              .withLastname(String.format("lastname %s", i))
              .withNickname(String.format("nickname %s", i))
              .withTitle(String.format("title %s", i))
              .withAddress(String.format("address %s", i))
              .withCompany(String.format("company %s", i))
              .withHomePhone(String.format("homePhone %s", i))
              .withMobilePhone(String.format("mobilePhone %s", i))
              .withWorkPhone(String.format("workPhone %s", i))
              .withFax(String.format("fax %s", i))
              .withEmail(String.format("emailFirst %s", i))
              .withEmail2(String.format("emailSecond %s", i))
              .withEmail3(String.format("emailThird %s", i))
              .withHomepage(String.format("homepage %s", i)))
     //         .withGroup(String.format("test1", i)))
      ;
    }
    return contacts;
  }


}
